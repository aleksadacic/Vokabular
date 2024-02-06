package com.aleksadacic.data.postgresql;

import com.aleksadacic.data.postgresql.utils.PojoConverter;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.QueryParameters;
import com.aleksadacic.engine.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class AppUserPersistenceManager<E extends BusinessEntity> extends SpringPersistenceManager {
    @Autowired
    UserRepository userRepository;

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BusinessEntity> List<T> getData(AppUser user, Class<T> clazz, QueryParameters parameters) {
        List<User> all = userRepository.findAll();
        return (List<T>) all.stream().map(e -> (AppUser) PojoConverter.convert(e, AppUser.class)).toList();
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
