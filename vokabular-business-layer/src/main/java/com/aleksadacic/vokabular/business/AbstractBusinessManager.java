package com.aleksadacic.vokabular.business;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.business.BusinessManager;
import com.aleksadacic.engine.framework.business.BusinessSpecificator;
import com.aleksadacic.engine.framework.persistence.DataOperation;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.engine.framework.persistence.SpringPersistenceDispatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractBusinessManager<T extends BusinessEntity> implements BusinessManager<T> {
    @Autowired
    private SpringPersistenceDispatcher dispatcher;

    protected PersistenceManager<T> getPersistenceManager(Class<T> clazz) {
        return dispatcher.getPersistenceManager(clazz);
    }

    protected abstract Class<T> getEntityClass();

    @Override
    public T update(T entity) throws TurboException {
        Optional<T> optional = dispatcher.getPersistenceManager(getEntityClass()).getById(SpringContext.getCurrentUser(), getEntityClass(), entity.getId());
        if (optional.isPresent()) {
            BusinessEntity found = optional.get();
            found.onBeforeUpdate();
            found.onBeforeSave();
            found.validateUpdate();
            found.validateSave();
            dispatcher.getPersistenceManager(getEntityClass()).update(SpringContext.getCurrentUser(), getEntityClass(), entity.getId(), entity);
        }
//        TODO throw exception
        return null;
    }

    @Override
    public T insert(T entity) throws TurboException {
        entity.onBeforeInsert();
        entity.onBeforeSave();
        entity.validateInsert();
        entity.validateSave();
        return dispatcher.getPersistenceManager(getEntityClass()).insert(SpringContext.getCurrentUser(), getEntityClass(), entity);
    }

    @Override
    public void delete(T entity) {
        dispatcher.getPersistenceManager(getEntityClass()).delete(SpringContext.getCurrentUser(), getEntityClass(), entity.getId());
    }

    @Override
    public long count() {
        return dispatcher.getPersistenceManager(getEntityClass()).count(SpringContext.getCurrentUser(), getEntityClass(), null);

    }

    @Override
    public long count(BusinessSpecificator<T> specification) {
        return dispatcher.getPersistenceManager(getEntityClass()).count(SpringContext.getCurrentUser(), getEntityClass(), specification.getSpecification());
    }

    @Override
    public List<T> getData() {
        return dispatcher.getPersistenceManager(getEntityClass()).getData(SpringContext.getCurrentUser(), getEntityClass(), null);
    }

    @Override
    public List<T> getData(BusinessSpecificator<T> spec) {
        return dispatcher.getPersistenceManager(getEntityClass()).getData(SpringContext.getCurrentUser(), getEntityClass(), spec.getSpecification());
    }
//    TODO jos jedan getdata sa sort

    @Override
    public Optional<T> getById(Id id) {
        return dispatcher.getPersistenceManager(getEntityClass()).getById(SpringContext.getCurrentUser(), getEntityClass(), id);
    }

    @Override
    public <X> X execute(DataOperation operation, Class<X> returnType, T entity, Map<String, Object> additionalData) {
        return (X) dispatcher.getPersistenceManager(getEntityClass()).execute(SpringContext.getCurrentUser(), getEntityClass(), operation.name(), returnType, entity, additionalData);
    }
}
