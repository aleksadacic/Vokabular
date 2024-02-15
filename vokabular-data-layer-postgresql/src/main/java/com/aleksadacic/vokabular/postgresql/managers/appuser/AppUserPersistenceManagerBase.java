package com.aleksadacic.vokabular.postgresql.managers.appuser;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.vokabular.postgresql.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AppUserPersistenceManagerBase implements PersistenceManager<AppUser> {
    @Autowired
    protected UserRepository repository;

    @Override
    public List<AppUser> getData(AppUser user, Class<AppUser> clazz, Specification<AppUser> parameters) {
        Specification<User> userSpecification = ObjectConverter.copySpecification(parameters);
        List<User> data = repository.findAll(userSpecification);
        List<AppUser> businessData = new ArrayList<>();
        for (User instance : data) {
            businessData.add(ObjectConverter.convert(instance, AppUser.class));
        }
        return businessData;
    }

    @Override
    public Optional<AppUser> getById(AppUser user, Class<AppUser> clazz, Id primaryKey) {
        Optional<User> dataObject = repository.findById(primaryKey.getValue());
        if (dataObject.isPresent()) {
            return Optional.ofNullable(ObjectConverter.convert(dataObject, AppUser.class));
        }
        return Optional.empty();
    }

    @Override
    public long count(AppUser user, Class<AppUser> clazz, Specification<AppUser> parameters) {
        return 0;
    }

    @Override
    public AppUser insert(AppUser appUser, Class<AppUser> clazz, BusinessEntity entity) {
        return null;
    }

    @Override
    public AppUser update(AppUser user, Class<AppUser> clazz, Id primaryKey, BusinessEntity entity) {
        return null;
    }

    @Override
    public void delete(AppUser user, Class<AppUser> clazz, Id primaryKey) {

    }

    @Override
    public <X> X execute(AppUser user, Class<AppUser> clazz, String executeId, Class<X> returnType, AppUser entity, Map<String, Object> additionalData) {
        return null;
    }
}
