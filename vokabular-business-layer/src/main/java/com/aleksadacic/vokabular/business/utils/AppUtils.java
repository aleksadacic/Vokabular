package com.aleksadacic.vokabular.business.utils;

import com.aleksadacic.engine.user.AppUser;

public class AppUtils {
    private AppUtils() {
        //prevents instantiation
    }

    public static AppUser getCurrentUser() {
        return new AppUser();
//        // Get the authentication object from the SecurityContextHolder
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // Retrieve details about the current user
//        String username = authentication.getName();
////        TODO
//        System.out.println(PersistenceDispatcher.getPersistenceDispatcher().getClass().getSimpleName());
//        return new AppUserManager().getData().stream().filter(e -> e.getUsername().equals(username)).toList().get(0);
    }
}
