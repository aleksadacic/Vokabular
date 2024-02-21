package com.aleksadacic.vokabular.business.users;

import com.aleksadacic.engine.framework.business.BusinessAttribute;
import com.aleksadacic.engine.framework.business.SpecificationContainer;
import com.aleksadacic.engine.framework.querying.Filter;
import com.aleksadacic.engine.framework.querying.SearchOperator;
import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.vokabular.business.BusinessSpecification;
import org.springframework.data.jpa.domain.Specification;

abstract class AppUserSpecificationBase implements SpecificationContainer<AppUser> {
    private Specification<AppUser> specification;

    protected AppUserSpecificationBase(Filter filter) {
        this.specification = Specification.where(new BusinessSpecification<>(filter));
    }

    public static AppUserSpecification where(BusinessAttribute key, SearchOperator operation, Object value) {
        return new AppUserSpecification(new Filter(key, operation, value));
    }

    public static AppUserSpecification where(BusinessAttribute key, Object value) {
        return new AppUserSpecification(new Filter(key, value));
    }

    public AppUserSpecification and(BusinessAttribute key, Object value) {
        specification = specification.and(new BusinessSpecification<>(new Filter(key, value)));
        return (AppUserSpecification) this;
    }

    public AppUserSpecification and(BusinessAttribute key, SearchOperator operation, Object value) {
        specification = specification.and(new BusinessSpecification<>(new Filter(key, operation, value)));
        return (AppUserSpecification) this;
    }

    public AppUserSpecification and(AppUserSpecification userSpec) {
        this.specification = specification.and(userSpec.getSpecification());
        return (AppUserSpecification) this;
    }

    public AppUserSpecification or(BusinessAttribute key, Object value) {
        specification = specification.or(new BusinessSpecification<>(new Filter(key, value)));
        return (AppUserSpecification) this;
    }

    public AppUserSpecification or(BusinessAttribute key, SearchOperator operation, Object value) {
        specification = specification.or(new BusinessSpecification<>(new Filter(key, operation, value)));
        return (AppUserSpecification) this;
    }

    public AppUserSpecification or(AppUserSpecification userSpec) {
        this.specification = specification.or(userSpec.getSpecification());
        return (AppUserSpecification) this;
    }

    @Override
    public Specification<AppUser> getSpecification() {
        return specification;
    }
}
