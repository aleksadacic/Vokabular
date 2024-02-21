package com.aleksadacic.vokabular.postgresql.managers.appuser;

import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.vokabular.postgresql.utils.ObjectConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppUserPersistenceManager extends AppUserPersistenceManagerBase {

    @SuppressWarnings("unchecked")
    @Override
    public <X> X execute(com.aleksadacic.engine.user.AppUser appUser, Class<com.aleksadacic.engine.user.AppUser> clazz, String executeId, Class<X> returnType, com.aleksadacic.engine.user.AppUser entity, Map<String, Object> additionalData) throws TurboException {
        if (executeId.equals("REGISTER_USER")) {
            AppUserEntity user = ObjectConverter.convert(entity, AppUserEntity.class);
            user.setPassword(entity.getPassword());
            user = getRepository().save(user);
            return (X) ObjectConverter.convert(user, clazz);
        }
        return super.execute(appUser, clazz, executeId, returnType, entity, additionalData);
    }
}
