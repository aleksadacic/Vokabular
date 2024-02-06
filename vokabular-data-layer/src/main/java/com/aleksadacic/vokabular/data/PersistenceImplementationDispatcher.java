package com.aleksadacic.vokabular.data;

import com.aleksadacic.data.postgresql.PostgrePersistenceDispatcher;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceDispatcher;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class PersistenceImplementationDispatcher implements PersistenceDispatcher {
    private final PostgrePersistenceDispatcher postgrePersistenceDispatcher = new PostgrePersistenceDispatcher();

    @Override
    public <T extends BusinessEntity> PersistenceManager getPersistenceManager(Class<T> clazz) {
        return new PostgrePersistenceDispatcher().getPersistenceManager(clazz);
    }
}
