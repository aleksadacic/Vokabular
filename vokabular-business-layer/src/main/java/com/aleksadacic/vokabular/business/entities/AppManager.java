package com.aleksadacic.vokabular.business.entities;

import com.aleksadacic.AppPersistenceManager;
import com.aleksadacic.engine.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppManager {
    @Autowired
    AppPersistenceManager appPersistenceManager;

    public AppUser login(String username, String password) {
        System.out.println("apppersistence: " + appPersistenceManager == null);
//        AppUser appUser = PersistenceDispatcher.getPersistenceDispatcher().getPersistenceManager().login(username, password);
        return null;
    }
}
