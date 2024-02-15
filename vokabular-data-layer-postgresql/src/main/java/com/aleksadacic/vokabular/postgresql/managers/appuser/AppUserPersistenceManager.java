package com.aleksadacic.vokabular.postgresql.managers.appuser;

import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.vokabular.postgresql.utils.ObjectConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppUserPersistenceManager extends AppUserPersistenceManagerBase {

    @SuppressWarnings("unchecked")
    @Override
    public <X> X execute(AppUser appUser, Class<AppUser> clazz, String executeId, Class<X> returnType, AppUser entity, Map<String, Object> additionalData) {
        if (executeId.equals("REGISTER_USER")) {
            User user = ObjectConverter.convert(entity, User.class);
            user.setPassword(entity.getPassword());
            user = repository.save(user);
            return (X) ObjectConverter.convert(user, clazz);
        }
        return super.execute(appUser, clazz, executeId, returnType, entity, additionalData);
    }
}
