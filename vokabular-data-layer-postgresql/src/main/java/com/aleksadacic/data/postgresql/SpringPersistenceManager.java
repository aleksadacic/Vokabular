package com.aleksadacic.data.postgresql;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.engine.framework.persistence.QueryParameters;
import com.aleksadacic.engine.user.AppUser;

import java.util.List;
import java.util.Map;

public abstract class SpringPersistenceManager implements PersistenceManager {
    //    TODO nesto preko repository
    @Override
    public <T extends BusinessEntity> List<T> getData(AppUser user, Class<T> clazz, QueryParameters parameters) {
        return null;
    }

    @Override
    public <T extends BusinessEntity> T getById(AppUser user, Class<T> clazz, Id primaryKey) {
        return null;
    }

    @Override
    public <T extends BusinessEntity> long count(AppUser user, Class<T> clazz, QueryParameters parameters) {
        return 0;
    }

    @Override
    public <T extends BusinessEntity> void insert(AppUser user, Class<T> clazz, BusinessEntity entity) {

    }

    @Override
    public <T extends BusinessEntity> void update(AppUser user, Class<T> clazz, Id primaryKey, BusinessEntity entity) {

    }

    @Override
    public <T extends BusinessEntity> void delete(AppUser user, Class<T> clazz, Id primaryKey) {

    }

    @Override
    public <T extends BusinessEntity, X> X execute(AppUser user, Class<T> clazz, String executeId, Class<X> returnType, Map<String, Object> additionalData) {
        return null;
    }
}
