package com.aleksadacic.vokabular.business.entities.word;

import com.aleksadacic.engine.framework.querying.EmptyFilter;
import org.springframework.data.jpa.domain.Specification;
import com.aleksadacic.vokabular.business.BusinessSpecification;
import com.aleksadacic.engine.framework.querying.SearchOperator;
import com.aleksadacic.engine.framework.querying.Filter;
import com.aleksadacic.engine.framework.business.SpecificationContainer;
import com.aleksadacic.engine.framework.business.BusinessAttribute;

abstract class WordSpecificationBase implements SpecificationContainer<Word> {
	private Specification<Word> specification;

	protected WordSpecificationBase(Filter filter) {
		this.specification = Specification.where(new BusinessSpecification<>(filter));
	}

	public static WordSpecification get() {
		return new WordSpecification(new EmptyFilter());
	}

	public static WordSpecification where(BusinessAttribute key, SearchOperator operation, Object value) {
		return new WordSpecification(new Filter(key, operation, value));
	}

	public static WordSpecification where(BusinessAttribute key, Object value) {
		return new WordSpecification(new Filter(key, value));
	}

	public WordSpecification and(BusinessAttribute key, Object value) {
		specification = specification.and(new BusinessSpecification<>(new Filter(key, value)));
		return (WordSpecification) this;
	}

	public WordSpecification and(BusinessAttribute key, SearchOperator operation, Object value) {
		specification = specification.and(new BusinessSpecification<>(new Filter(key, operation, value)));
		return (WordSpecification) this;
	}

	public WordSpecification and(WordSpecification userSpec) {
		specification = specification.and(userSpec.getSpecification());
		return (WordSpecification) this;
	}

	public WordSpecification or(BusinessAttribute key, Object value) {
		specification = specification.or(new BusinessSpecification<>(new Filter(key, value)));
		return (WordSpecification) this;
	}

	public WordSpecification or(BusinessAttribute key, SearchOperator operation, Object value) {
		specification = specification.or(new BusinessSpecification<>(new Filter(key, operation, value)));
		return (WordSpecification) this;
	}

	public WordSpecification or(WordSpecification userSpec) {
		specification = specification.or(userSpec.getSpecification());
		return (WordSpecification) this;
	}

	@Override
	public Specification<Word> getSpecification() { return specification; }
}
