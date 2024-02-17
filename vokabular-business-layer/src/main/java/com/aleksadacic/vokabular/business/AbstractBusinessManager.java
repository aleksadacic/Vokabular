package com.aleksadacic.vokabular.business;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.exceptions.DataNotFoundException;
import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.business.BusinessManager;
import com.aleksadacic.engine.framework.business.BusinessSpecificator;
import com.aleksadacic.engine.framework.persistence.DataOperation;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractBusinessManager<T extends BusinessEntity> implements BusinessManager<T> {

    protected PersistenceManager<T> getPersistenceManager(Class<T> clazz) {
        return SpringContext.getPersistenceManager(clazz);
    }

    protected abstract Class<T> getEntityClass();

    @Override
    public T update(T entity) throws TurboException {
        Optional<T> optional = getPersistenceManager(getEntityClass()).getById(SpringContext.getCurrentUser(), getEntityClass(), entity.getId());
        if (optional.isPresent()) {
            BusinessEntity found = optional.get();
            found.onBeforeUpdate();
            found.onBeforeSave();
            found.validateUpdate();
            found.validateSave();
            getPersistenceManager(getEntityClass()).update(SpringContext.getCurrentUser(), getEntityClass(), entity.getId(), entity);
        }
        throw new DataNotFoundException();
    }

    @Override
    public T insert(T entity) throws TurboException {
        entity.onBeforeInsert();
        entity.onBeforeSave();
        entity.validateInsert();
        entity.validateSave();
        return getPersistenceManager(getEntityClass()).insert(SpringContext.getCurrentUser(), getEntityClass(), entity);
    }

    @Override
    public void delete(T entity) throws TurboException {
        getPersistenceManager(getEntityClass()).delete(SpringContext.getCurrentUser(), getEntityClass(), entity.getId());
    }

    @Override
    public long count() throws TurboException {
        return getPersistenceManager(getEntityClass()).count(SpringContext.getCurrentUser(), getEntityClass(), null);

    }

    @Override
    public long count(BusinessSpecificator<T> specification) throws TurboException {
        return getPersistenceManager(getEntityClass()).count(SpringContext.getCurrentUser(), getEntityClass(), specification.getSpecification());
    }

    @Override
    public List<T> getData() throws TurboException {
        return getPersistenceManager(getEntityClass()).getData(SpringContext.getCurrentUser(), getEntityClass(), null);
    }

    @Override
    public List<T> getData(BusinessSpecificator<T> spec) throws TurboException {
        return getPersistenceManager(getEntityClass()).getData(SpringContext.getCurrentUser(), getEntityClass(), spec.getSpecification());
    }

    @Override
    public List<T> getData(BusinessSpecificator<T> spec, Sort sort) throws TurboException {
        return getPersistenceManager(getEntityClass()).getData(SpringContext.getCurrentUser(), getEntityClass(), spec.getSpecification());
    }

    @Override
    public T getUnique(BusinessSpecificator<T> spec) throws TurboException {
        try {
            return getPersistenceManager(getEntityClass()).getData(SpringContext.getCurrentUser(), getEntityClass(), spec.getSpecification()).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new DataNotFoundException();
        }
    }

    @Override
    public Optional<T> getById(Id id) throws TurboException {
        return getPersistenceManager(getEntityClass()).getById(SpringContext.getCurrentUser(), getEntityClass(), id);
    }

    @Override
    public <X> X execute(DataOperation operation, Class<X> returnType, T entity, Map<String, Object> additionalData) throws TurboException {
        return getPersistenceManager(getEntityClass()).execute(SpringContext.getCurrentUser(), getEntityClass(), operation.name(), returnType, entity, additionalData);
    }
}
