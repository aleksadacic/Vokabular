package com.aleksadacic.vokabular.business;

import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.framework.querying.Filter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

public class BusinessSpecification<T extends BusinessEntity> implements Specification<T> {
    private final transient Filter filter;

    public BusinessSpecification(Filter filter) {
        this.filter = filter;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {
        Comparable value;
        if (filter.getValue() instanceof BusinessEntity) {
            value = ((BusinessEntity) filter.getValue()).getId().getValue();
        } else {
            value = (Comparable) filter.getValue();
        }

        switch (filter.getOperation()) {
            case GTE:
                return builder.greaterThanOrEqualTo(root.get(filter.getKey()), value);
            case GT:
                return builder.greaterThan(root.get(filter.getKey()), value);
            case LTE:
                return builder.lessThanOrEqualTo(root.get(filter.getKey()), value);
            case LT:
                return builder.lessThan(root.get(filter.getKey()), value);
            case NOT_EQUAL:
                return builder.notEqual(root.get(filter.getKey()), value);
            case EQUAL:
                return builder.equal(root.get(filter.getKey()), value);

            case STARTS_WITH:
                if (root.get(filter.getKey()).getJavaType() == String.class) {
                    return builder.like(root.get(filter.getKey()), value + "%");
                } else {
                    return builder.equal(root.get(filter.getKey()), value);
                }
            case ENDS_WITH:
                if (root.get(filter.getKey()).getJavaType() == String.class) {
                    return builder.like(root.get(filter.getKey()), "%" + value);
                } else {
                    return builder.equal(root.get(filter.getKey()), value);
                }

            case LIKE:
                if (root.get(filter.getKey()).getJavaType() == String.class) {
                    return builder.like(root.get(filter.getKey()), "%" + value + "%");
                } else {
                    return builder.equal(root.get(filter.getKey()), value);
                }
            default:
                return null;
        }
    }
}
