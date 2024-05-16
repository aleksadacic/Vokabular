package com.aleksadacic.vokabular.business.entities.phrase;

import com.aleksadacic.engine.framework.querying.EmptyFilter;
import com.aleksadacic.engine.framework.querying.SearchOperator;
import com.aleksadacic.engine.framework.business.BusinessAttribute;
import com.aleksadacic.engine.framework.querying.Filter;

public class PhraseSpecification extends PhraseSpecificationCustom {
	public PhraseSpecification(Filter filter) { super(filter); }
	public static PhraseSpecification get() {
		return new PhraseSpecification(new EmptyFilter());
	}

	public static PhraseSpecification where(BusinessAttribute key, SearchOperator operation, Object value) {
		return new PhraseSpecification(new Filter(key, operation, value));
	}

	public static PhraseSpecification where(BusinessAttribute key, Object value) {
		return new PhraseSpecification(new Filter(key, value));
	}

}
