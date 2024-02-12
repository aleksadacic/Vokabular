package com.aleksadacic.vokabular.postgresql.managers.appuser;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.engine.framework.persistence.QueryParameters;
import com.aleksadacic.engine.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AppUserPersistenceManagerBase implements PersistenceManager<AppUser> {
    @Autowired
    protected UserRepository repository;

    @Override
    public List<AppUser> getData(AppUser user, Class<AppUser> clazz, QueryParameters parameters) {
        List<User> data = repository.findAll();
        List<AppUser> businessData = new ArrayList<>();
        for (User instance : data) {
            AppUser businessInstance = new AppUser();
            businessInstance.setUsername(instance.getUsername());
            businessInstance.setPassword(instance.getPassword());
            businessInstance.setId(Id.of(instance.getId()));
            businessData.add(businessInstance);
        }
        return businessData;
    }

    @Override
    public AppUser getById(AppUser user, Class<AppUser> clazz, Id primaryKey) {
        return null;
    }

    @Override
    public long count(AppUser user, Class<AppUser> clazz, QueryParameters parameters) {
        return 0;
    }

    @Override
    public void insert(AppUser user, Class<AppUser> clazz, BusinessEntity entity) {

    }

    @Override
    public void update(AppUser user, Class<AppUser> clazz, Id primaryKey, BusinessEntity entity) {

    }

    @Override
    public void delete(AppUser user, Class<AppUser> clazz, Id primaryKey) {

    }

    @Override
    public Object execute(AppUser user, Class<AppUser> clazz, String executeId, Class<?> returnType, Map<String, Object> additionalData) {
        return null;
    }
}
