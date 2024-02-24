package com.aleksadacic.vokabular.business;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.exceptions.DataNotFoundException;
import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.business.BusinessManager;
import com.aleksadacic.engine.framework.business.DataProperties;
import com.aleksadacic.engine.framework.business.SpecificationContainer;
import com.aleksadacic.engine.framework.persistence.DataOperation;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractBusinessManager<T extends BusinessEntity> implements BusinessManager<T> {
    private SpecificationContainer<T> specification;
    private Pageable pageable;
    private Sort sort;

    protected PersistenceManager<T> getPersistenceManager(Class<T> clazz) {
        return SpringContext.getPersistenceManager(clazz);
    }

    protected abstract Class<T> getEntityClass();

    @Override
    public T create() throws TurboException {
        try {
//            TODO defaults...
            return getEntityClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new TurboException(e);
        }
    }

    @Override
    public T update(T entity) throws TurboException {
        Optional<T> optional = getPersistenceManager(getEntityClass()).getById(SpringContext.getCurrentUser(), getEntityClass(), entity.getId());
        if (optional.isPresent()) {
            BusinessEntity found = optional.get();
            found.onBeforeUpdate();
            found.onBeforeSave();
            found.validateUpdate();
            found.validateSave();
            return getPersistenceManager(getEntityClass()).update(SpringContext.getCurrentUser(), entity.getId(), entity);
        }
        throw new DataNotFoundException();
    }

    @Override
    public T insert(T entity) throws TurboException {
        entity.onBeforeInsert();
        entity.onBeforeSave();
        entity.validateInsert();
        entity.validateSave();
        return getPersistenceManager(getEntityClass()).insert(SpringContext.getCurrentUser(), entity);
    }

    @Override
    public void delete(T entity) throws TurboException {
        getPersistenceManager(getEntityClass()).delete(SpringContext.getCurrentUser(), getEntityClass(), entity.getId());
    }

    @Override
    public long count() throws TurboException {
        if (specification != null)
            return getPersistenceManager(getEntityClass()).count(SpringContext.getCurrentUser(), getEntityClass(), specification.getSpecification());
        return getPersistenceManager(getEntityClass()).count(SpringContext.getCurrentUser(), getEntityClass(), null);
    }


    @Override
    public long count(SpecificationContainer<T> specification) throws TurboException {
        this.specification = specification;
        return count();
    }

    @Override
    public void setSpecification(SpecificationContainer<T> specification) {
        this.specification = specification;
    }

    @Override
    public void setPageable(Pageable page) {
        this.pageable = page;
    }

    @Override
    public void setPageable(int page, int size) {
        this.pageable = PageRequest.of(page, size);
    }

    @Override
    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public List<T> getData() throws TurboException {
        return getPersistenceManager(getEntityClass()).getData(SpringContext.getCurrentUser(), getEntityClass(), collectProperties());
    }

    @Override
    public List<T> getData(SpecificationContainer<T> spec) throws TurboException {
        this.specification = spec;
        return getData();
    }

    @Override
    public Page<T> getPageData() throws TurboException {
        return getPersistenceManager(getEntityClass()).getPageData(SpringContext.getCurrentUser(), getEntityClass(), collectProperties());
    }

    @Override
    public Page<T> getPageData(SpecificationContainer<T> specification) throws TurboException {
        this.specification = specification;
        return getPageData();
    }

    @Override
    public T getUnique(SpecificationContainer<T> spec) throws TurboException {
        try {
            this.pageable = PageRequest.of(0, 1);
            this.specification = spec;
            return getUnique();
        } catch (IndexOutOfBoundsException e) {
            throw new DataNotFoundException();
        }
    }

    @Override
    public T getUnique() throws TurboException {
        return getPersistenceManager(getEntityClass()).getData(SpringContext.getCurrentUser(), getEntityClass(), collectProperties()).get(0);
    }

    @Override
    public Optional<T> getById(Id id) throws TurboException {
        return getPersistenceManager(getEntityClass()).getById(SpringContext.getCurrentUser(), getEntityClass(), id);
    }

    @Override
    public <X> X execute(DataOperation operation, Class<X> returnType, T entity, Map<String, Object> additionalData) throws TurboException {
        return getPersistenceManager(getEntityClass()).execute(SpringContext.getCurrentUser(), getEntityClass(), operation.name(), returnType, entity, additionalData);
    }

    private DataProperties<T> collectProperties() {
        DataProperties<T> properties = new DataProperties<>();
        properties.setPage(pageable);
        properties.setSort(sort);
        properties.setContainer(specification);
        return properties;
    }
}
