package com.aleksadacic.vokabular.business.users;

import com.aleksadacic.engine.framework.business.BusinessSpecificator;
import com.aleksadacic.engine.framework.business.SpecialOperation;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.engine.framework.persistence.SpringPersistenceDispatcher;
import com.aleksadacic.engine.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthManager {
    @Autowired
    private SpringPersistenceDispatcher dispatcher;

    public AppUser getByUsername(String username) {
        AppUserSpecification spec = AppUserSpecification.where(AppUserAttribute.USERNAME, username);
        return getData(spec).get(0);
    }

    public AppUser register(String username, String password) {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        return getPersistenceManager().execute(null, AppUser.class, SpecialOperation.REGISTER_USER.name(), AppUser.class, user, null);
    }

    protected PersistenceManager<AppUser> getPersistenceManager() {
        return dispatcher.getPersistenceManager(AppUser.class);
    }

    public List<AppUser> getData(BusinessSpecificator<AppUser> spec) {
        return dispatcher.getPersistenceManager(AppUser.class).getData(null, AppUser.class, spec.getSpecification());
    }
}
