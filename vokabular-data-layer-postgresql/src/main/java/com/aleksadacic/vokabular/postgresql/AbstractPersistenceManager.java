package com.aleksadacic.vokabular.postgresql;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.exceptions.DataNotFoundException;
import com.aleksadacic.engine.exceptions.PageUnspecifiedException;
import com.aleksadacic.engine.exceptions.PersistenceException;
import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.business.DataProperties;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import com.aleksadacic.engine.framework.persistence.PersistenceEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.vokabular.postgresql.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

public abstract class AbstractPersistenceManager<T extends BusinessEntity, P extends PersistenceEntity> implements PersistenceManager<T> {
    protected Class<P> persistenceEntityClass;
    protected Class<T> businessEntityClass;

    protected abstract DataEntityRepository<P> getRepository();

    protected abstract T convertToBusinessEntity(P source);

    protected abstract P convertToPersistenceEntity(T source);

    protected AbstractPersistenceManager(Class<T> businessEntityClass, Class<P> persistenceEntityClass) {
        this.persistenceEntityClass = persistenceEntityClass;
        this.businessEntityClass = businessEntityClass;
    }

    private Pageable createPageable(DataProperties<T> properties) throws PageUnspecifiedException {
        Sort sort = properties.getSort();
        Pageable page = properties.getPage();
        if (page == null) {
            throw new PageUnspecifiedException();
        }
        if (sort == null) {
            return page;
        }
        return PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
    }

    @Override
    public Page<T> getPageData(AppUser user, Class<T> clazz, DataProperties<T> properties) throws TurboException {
        Specification<P> dataSpec = DataUtils.copySpecification(properties.getSpecification());
        Pageable page = createPageable(properties);
        Page<P> data = getRepository().findAll(dataSpec, page);
        return data.map(this::convertToBusinessEntity);
    }

    @Override
    public List<T> getData(AppUser user, Class<T> clazz, DataProperties<T> properties) throws TurboException {
        Specification<P> userSpecification = DataUtils.copySpecification(properties.getSpecification());
        List<P> all;
        try {
            Pageable page = createPageable(properties);
            Page<P> data = getRepository().findAll(userSpecification, page);
            all = data.getContent();
        } catch (PageUnspecifiedException e) {
            all = getRepository().findAll(userSpecification);
        }
        List<T> businessData = new ArrayList<>();
        for (P instance : all) {
            businessData.add(convertToBusinessEntity(instance));
        }
        return businessData;
    }

    @Override
    public Optional<T> getById(AppUser user, Class<T> clazz, Id primaryKey) throws TurboException {
        Optional<P> dataObject = getRepository().findById(primaryKey.getValue());
        return dataObject.map(this::convertToBusinessEntity);
    }

    @Override
    public long count(AppUser user, Class<T> clazz, Specification<T> spec) throws TurboException {
        try {
            Specification<P> dataSpec = DataUtils.copySpecification(spec);
            return getRepository().count(dataSpec);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public T insert(AppUser user, T entity) throws TurboException {
        try {
            P insert = convertToPersistenceEntity(entity);
            insert = getRepository().save(insert);
            return convertToBusinessEntity(insert);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public T update(AppUser user, Id primaryKey, T entity) throws TurboException {
        try {
            if (!getRepository().existsById(primaryKey.getValue())) {
                throw new DataNotFoundException();
            }
            P update = convertToPersistenceEntity(entity);
            update.setId(primaryKey.getValue());
            update = getRepository().save(update);
            return convertToBusinessEntity(update);
        } catch (PersistenceException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public void delete(AppUser user, Class<T> clazz, Id primaryKey) throws TurboException {
        try {
            getRepository().delete(getRepository().findById(primaryKey.getValue()).orElseThrow());
        } catch (NoSuchElementException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public <X> X execute(AppUser user, Class<T> clazz, String executeId, Class<X> returnType, T entity, Map<String, Object> additionalData) throws TurboException {
        return null;
    }
}
