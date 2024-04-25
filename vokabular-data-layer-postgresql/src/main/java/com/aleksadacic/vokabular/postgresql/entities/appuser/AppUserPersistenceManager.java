package com.aleksadacic.vokabular.postgresql.entities.appuser;

import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.model.annotations.EntityPersistenceManager;
import com.aleksadacic.engine.user.AppUser;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EntityPersistenceManager(businessClass = AppUser.class)
public class AppUserPersistenceManager extends AppUserPersistenceManagerBase {

    @SuppressWarnings("unchecked")
    @Override
    public <X> X execute(com.aleksadacic.engine.user.AppUser appUser, Class<com.aleksadacic.engine.user.AppUser> clazz, String executeId, Class<X> returnType, com.aleksadacic.engine.user.AppUser entity, Map<String, Object> additionalData) throws TurboException {
        if (executeId.equals("REGISTER_USER")) {
            AppUserEntity user = convertToPersistenceEntity(appUser);
            user.setPassword(entity.getPassword());
            user = getRepository().save(user);
            return (X) convertToBusinessEntity(user);
        }
        return super.execute(appUser, clazz, executeId, returnType, entity, additionalData);
    }
}
