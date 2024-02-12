package com.aleksadacic.vokabular.postgresql.managers.appuser;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.user.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserPersistenceManager extends AppUserPersistenceManagerBase {

    @Override
    public AppUser getById(AppUser user, Class<AppUser> clazz, Id primaryKey) {
        return user;
    }
}
