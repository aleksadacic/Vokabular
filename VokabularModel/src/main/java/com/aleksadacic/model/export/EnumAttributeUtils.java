package com.aleksadacic.model.export;

import com.aleksadacic.engine.model.annotations.EnumAttribute;

import java.lang.reflect.Field;

public class EnumAttributeUtils {
    private final Field field;

    EnumAttributeUtils(Field field) {
        this.field = field;
    }

    String extractName() {
        return field.getName();
    }

    String extractValue() {
        EnumAttribute enumAttribute = field.getAnnotation(EnumAttribute.class);
        if (enumAttribute != null) {
            return enumAttribute.value();
        }
        return null;
    }

    String extractDefaultValue() {
        EnumAttribute enumAttribute = field.getAnnotation(EnumAttribute.class);
        if (enumAttribute != null) {
            return enumAttribute.value().toLowerCase();
        }
        return null;
    }
}
