package com.aleksadacic.vokabular.postgresql;

import com.aleksadacic.engine.framework.SpringContextAware;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceDispatcher;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import org.springframework.stereotype.Component;

@Component
public class PostgrePersistenceDispatcher implements PersistenceDispatcher {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends BusinessEntity> PersistenceManager<T> getPersistenceManager(Class<T> clazz) {
        try {
            return (PersistenceManager<T>) SpringContextAware.getBean(Class.forName(this.getClass().getPackageName() + ".managers." + clazz.getSimpleName().toLowerCase() + "." + clazz.getSimpleName() + "PersistenceManager"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
