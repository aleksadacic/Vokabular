package com.aleksadacic.vokabular.postgresql.utils;

import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class DataUtils {
    private DataUtils() {
        // prevents instantiation
    }

    @SuppressWarnings("unchecked")
    public static <T, U> Specification<U> copySpecification(Specification<T> originalSpec) {
        if (originalSpec == null) return null;
        return (root, query, criteriaBuilder) -> originalSpec.toPredicate((Root<T>) root, query, criteriaBuilder);
    }
}
