package com.aleksadacic.data.postgresql;

import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceDispatcher;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import org.springframework.beans.factory.annotation.Autowired;

public class PostgrePersistenceDispatcher implements PersistenceDispatcher {
    @Autowired
    AppUserPersistenceManager persistenceManager;

    @Override
    public <T extends BusinessEntity> PersistenceManager getPersistenceManager(Class<T> clazz) {
        try {
//            Class<?> pmClass = ClassUtils.findPersistenceManagerClass(clazz);
//            return AppUserPersistenceManager.class.getDeclaredConstructor().newInstance();
            return persistenceManager;
//            return (PersistenceManager) pmClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
