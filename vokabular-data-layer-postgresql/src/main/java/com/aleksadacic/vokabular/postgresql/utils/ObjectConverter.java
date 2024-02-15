package com.aleksadacic.vokabular.postgresql.utils;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ObjectConverter {
    private ObjectConverter() {
        // prevents instantiation
    }

    @SuppressWarnings("unchecked")
    public static <T, U> Specification<U> copySpecification(Specification<T> originalSpec) {
        return (root, query, criteriaBuilder) -> originalSpec.toPredicate((Root<T>) root, query, criteriaBuilder);
    }

    @SuppressWarnings("java:S3011")
    public static <T extends BusinessEntity, X> X convert(T source, Class<X> target) {
        X destination = null;
        try {
            destination = target.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        Class<?> sourceClass = source.getClass();

        Field[] sourceFields = sourceClass.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            try {
                Field destinationField = target.getDeclaredField(sourceField.getName());
                sourceField.setAccessible(true);
                destinationField.setAccessible(true);
                if (destinationField.getName().equals("id") && sourceField.get(source) != null) {
                    destinationField.set(destination, ((Id) sourceField.get(source)).getValue());
                } else {
                    destinationField.set(destination, sourceField.get(source));
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // just continue...
            }
        }
        return destination;
    }

    @SuppressWarnings("java:S3011")
    public static <T extends BusinessEntity, X> T convert(X source, Class<T> target) {
        try {
            T destination = target.getDeclaredConstructor().newInstance();
            Class<?> sourceClass = source.getClass();

            Field[] sourceFields = sourceClass.getDeclaredFields();

            for (Field sourceField : sourceFields) {
                try {
                    Field destinationField = target.getDeclaredField(sourceField.getName());
                    sourceField.setAccessible(true);
                    destinationField.setAccessible(true);
                    if (destinationField.getName().equals("id")) {
                        destinationField.set(destination, Id.of(sourceField.get(source)));
                    } else {
                        destinationField.set(destination, sourceField.get(source));
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // just continue...
                }
            }
            return destination;
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
