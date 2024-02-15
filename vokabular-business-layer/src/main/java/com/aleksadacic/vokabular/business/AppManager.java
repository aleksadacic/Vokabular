package com.aleksadacic.vokabular.business;

import com.aleksadacic.engine.framework.persistence.PersistenceDispatcher;
import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.vokabular.business.users.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppManager {
    @Autowired
    private PersistenceDispatcher dispatcher;

    @Autowired
    private AuthManager userManager;

    public AppUser getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            return userManager.getByUsername(authentication.getName());
//        }
        return null; // No user authenticated
    }

    @Override
    public String toString() {
        return dispatcher.toString();
    }
}
