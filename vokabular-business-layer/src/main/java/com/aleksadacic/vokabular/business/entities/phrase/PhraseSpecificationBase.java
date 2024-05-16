package com.aleksadacic.vokabular.business.entities.phrase;

import org.springframework.data.jpa.domain.Specification;
import com.aleksadacic.vokabular.business.BusinessSpecification;
import com.aleksadacic.engine.framework.querying.SearchOperator;
import com.aleksadacic.engine.framework.querying.Filter;
import com.aleksadacic.engine.framework.business.SpecificationContainer;
import com.aleksadacic.engine.framework.business.BusinessAttribute;

abstract class PhraseSpecificationBase implements SpecificationContainer<Phrase> {
	private Specification<Phrase> specification;

	protected PhraseSpecificationBase(Filter filter) {
		this.specification = Specification.where(new BusinessSpecification<>(filter));
	}

	public PhraseSpecification and(BusinessAttribute key, Object value) {
		specification = specification.and(new BusinessSpecification<>(new Filter(key, value)));
		return (PhraseSpecification) this;
	}

	public PhraseSpecification and(BusinessAttribute key, SearchOperator operation, Object value) {
		specification = specification.and(new BusinessSpecification<>(new Filter(key, operation, value)));
		return (PhraseSpecification) this;
	}

	public PhraseSpecification and(PhraseSpecification userSpec) {
		specification = specification.and(userSpec.getSpecification());
		return (PhraseSpecification) this;
	}

	public PhraseSpecification or(BusinessAttribute key, Object value) {
		specification = specification.or(new BusinessSpecification<>(new Filter(key, value)));
		return (PhraseSpecification) this;
	}

	public PhraseSpecification or(BusinessAttribute key, SearchOperator operation, Object value) {
		specification = specification.or(new BusinessSpecification<>(new Filter(key, operation, value)));
		return (PhraseSpecification) this;
	}

	public PhraseSpecification or(PhraseSpecification userSpec) {
		specification = specification.or(userSpec.getSpecification());
		return (PhraseSpecification) this;
	}

	@Override
	public Specification<Phrase> getSpecification() { return specification; }
}
