package com.aleksadacic.vokabular.postgresql;

import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceDispatcher;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.vokabular.postgresql.managers.appuser.AppUserPersistenceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostgrePersistenceDispatcher implements PersistenceDispatcher {
    @Autowired
    AppUserPersistenceManager persistenceManager;

    @Override
    public <T extends BusinessEntity> PersistenceManager<T> getPersistenceManager(Class<T> clazz) {
        try {
            return (PersistenceManager<T>) persistenceManager;
        } catch (Exception e) {
            return null;
        }
    }
}
