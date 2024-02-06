package com.aleksadacic.vokabular.data;

import com.aleksadacic.AppPersistenceManager;
import com.aleksadacic.engine.user.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppPersistenceManagerDispatcher implements AppPersistenceManager {

    @Override
    public AppUser login(String username, String password) {
        return null;
    }

    @Override
    public void logout(AppUser user) {

    }
}
