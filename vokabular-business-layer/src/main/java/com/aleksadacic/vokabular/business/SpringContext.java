package com.aleksadacic.vokabular.business;

import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.exceptions.UserNotAuthenticatedException;
import com.aleksadacic.engine.framework.SpringContextAware;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.engine.framework.persistence.SpringPersistenceDispatcher;
import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.vokabular.business.users.AuthManager;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringContext {
    private SpringContext() {
    }

    public static <T> T getBean(Class<T> beanClass) {
        return SpringContextAware.getBean(beanClass);
    }

    public static AppUser getCurrentUser() throws TurboException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthManager manager = SpringContextAware.getBean(AuthManager.class);
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return manager.getByUsername(authentication.getName());
        }
        throw new UserNotAuthenticatedException();
    }

    public static <T extends BusinessEntity> PersistenceManager<T> getPersistenceManager(Class<T> clazz) {
        SpringPersistenceDispatcher dispatcher = SpringContextAware.getBean(SpringPersistenceDispatcher.class);
        return dispatcher.getPersistenceManager(clazz);
    }
}
