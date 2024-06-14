package com.aleksadacic.model.export;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.model.annotations.ModelAttribute;

import java.lang.reflect.Field;

public class AttributeUtils {
    private final Field field;

    AttributeUtils(Field field) {
        this.field = field;
    }

    String extractName() {
        return field.getName();
    }

    String extractTitle() {
        return field.getAnnotation(ModelAttribute.class).title();
    }

    String extractDefaultValue() {
        return field.getAnnotation(ModelAttribute.class).defaultValue();
    }

    Integer extractMaxLength() {
        int value = field.getAnnotation(ModelAttribute.class).maxLength();
        return value >= 0 ? value : null;
    }

    Integer extractMinLength() {
        int value = field.getAnnotation(ModelAttribute.class).minLength();
        return value >= 0 ? value : null;
    }

    boolean extractNullable() {
        return field.getAnnotation(ModelAttribute.class).nullable();
    }

    boolean extractUnique() {
        return field.getAnnotation(ModelAttribute.class).unique();
    }

    String extractTableColumn() {
        return field.getAnnotation(ModelAttribute.class).tableColumn();
    }

    //TODO malo elegantnije da se smisli sve oko tipova i ovde i u turbo creator, da se vidi sta jos odavde da se prebaci u turbo creator
    public String extractType() {
        if (String.class.equals(field.getType())) {
            return "string";
        }
        if (boolean.class.equals(field.getType()) || Boolean.class.equals(field.getType()))
            return "boolean";

        if (int.class.equals(field.getType()) || Integer.class.equals(field.getType()))
            return "integer";

        if (Id.class.equals(field.getType())) {
            return "primaryKey";
        }
        return field.getType().getSimpleName();
    }

    public boolean extractEnumType() {
        return field.getType().isEnum();
    }

    public boolean extractCollection() {
        return field.getAnnotation(ModelAttribute.class).collection();
    }
}
