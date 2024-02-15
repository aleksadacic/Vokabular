package com.aleksadacic.vokabular.data;

import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.engine.framework.persistence.SpringPersistenceDispatcher;
import com.aleksadacic.vokabular.postgresql.PostgrePersistenceDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistenceDispatcherImpl implements SpringPersistenceDispatcher {
    @Autowired
    private PostgrePersistenceDispatcher postgrePersistenceDispatcher;

    public <T extends BusinessEntity> PersistenceManager<T> getPersistenceManager(Class<T> clazz) {
        return postgrePersistenceDispatcher.getPersistenceManager(clazz);
    }
}
