package com.aleksadacic.vokabular.data;

import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.vokabular.postgresql.PostgrePersistenceDispatcher;
import org.springframework.stereotype.Service;

@Service
public class PersistenceImplementationDispatcher {
    private final PostgrePersistenceDispatcher postgrePersistenceDispatcher = new PostgrePersistenceDispatcher();

    // TODO refleksija
    public <T extends BusinessEntity> PersistenceManager getPersistenceManager(Class<T> clazz) {
        return new PostgrePersistenceDispatcher().getPersistenceManager(clazz);
    }
}
