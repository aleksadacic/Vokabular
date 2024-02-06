package com.aleksadacic.model.export;

import com.aleksadacic.engine.model.annotations.ModelEntity;

//TODO implements nesto po schemi iz turbo generatora
public class EntityUtils {
    private final Class<?> clazz;

    EntityUtils(Class<?> clazz) {
        this.clazz = clazz;
    }

    String extractTableName() {
        return clazz.getAnnotation(ModelEntity.class).tableName();
    }

    String extractEntityTitle() {
        return clazz.getAnnotation(ModelEntity.class).title();
    }

    String extractEntityName() {
        return clazz.getSimpleName();
    }
}
