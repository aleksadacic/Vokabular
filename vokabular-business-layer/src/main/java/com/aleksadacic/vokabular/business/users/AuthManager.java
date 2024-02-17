package com.aleksadacic.vokabular.business.users;

import com.aleksadacic.engine.exceptions.DataNotFoundException;
import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.framework.business.BusinessSpecificator;
import com.aleksadacic.engine.framework.business.SpecialOperation;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.engine.framework.persistence.SpringPersistenceDispatcher;
import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.vokabular.business.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthManager {
    @Autowired
    private SpringPersistenceDispatcher dispatcher;

    public AppUser getByUsername(String username) throws TurboException {
        AppUserSpecification spec = AppUserSpecification.where(AppUserAttribute.USERNAME, username);
        return getUnique(spec);
    }

    public AppUser register(String username, String password) throws TurboException {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        return getPersistenceManager().execute(null, AppUser.class, SpecialOperation.REGISTER_USER.name(), AppUser.class, user, null);
    }

    protected PersistenceManager<AppUser> getPersistenceManager() {
        return dispatcher.getPersistenceManager(AppUser.class);
    }

    public AppUser getUnique(BusinessSpecificator<AppUser> spec) throws TurboException {
        try {
            return dispatcher.getPersistenceManager(AppUser.class).getData(SpringContext.getCurrentUser(), AppUser.class, spec.getSpecification()).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new DataNotFoundException();
        }
    }
}
