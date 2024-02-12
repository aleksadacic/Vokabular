package com.aleksadacic.vokabular.postgresql;

import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceDispatcher;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.vokabular.postgresql.managers.appuser.AppUserPersistenceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("postgresql")
public class PostgrePersistenceDispatcher implements PersistenceDispatcher {
    @Autowired
    AppUserPersistenceManager persistenceManager;

    @Override
    public <T extends BusinessEntity> PersistenceManager<T> getPersistenceManager(Class<T> clazz) {
        try {
//            Class<?> pmClass = ClassUtils.findPersistenceManagerClass(clazz);
//            return AppUserPersistenceManager.class.getDeclaredConstructor().newInstance();
            return (PersistenceManager<T>) persistenceManager;
//            return (PersistenceManager) pmClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
