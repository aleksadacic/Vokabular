package com.aleksadacic.vokabular.postgresql;

import com.aleksadacic.engine.framework.SpringContextAware;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.persistence.PersistenceDispatcher;
import com.aleksadacic.engine.framework.persistence.PersistenceManager;
import com.aleksadacic.engine.model.annotations.EntityPersistenceManager;
import com.aleksadacic.engine.reflections.ClassScanner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PostgrePersistenceDispatcher implements PersistenceDispatcher {
    private static final Map<Class<?>, Class<?>> map = new HashMap<>();

    static {
        for (Class<?> clazz : getPersistenceManagerClasses()) {
            map.put(clazz.getAnnotation(EntityPersistenceManager.class).businessClass(), clazz);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BusinessEntity> PersistenceManager<T> getPersistenceManager(Class<T> clazz) {
        try {
            return (PersistenceManager<T>) SpringContextAware.getBean(map.get(clazz));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<Class<?>> getPersistenceManagerClasses() {
        return ClassScanner.getClassesByAnnotation(EntityPersistenceManager.class);
    }
}
