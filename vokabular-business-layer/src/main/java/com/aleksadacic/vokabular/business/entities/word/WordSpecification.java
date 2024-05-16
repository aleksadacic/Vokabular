package com.aleksadacic.vokabular.business.entities.word;

import com.aleksadacic.engine.framework.querying.EmptyFilter;
import com.aleksadacic.engine.framework.querying.SearchOperator;
import com.aleksadacic.engine.framework.business.BusinessAttribute;
import com.aleksadacic.engine.framework.querying.Filter;

public class WordSpecification extends WordSpecificationCustom {
	public WordSpecification(Filter filter) { super(filter); }
	public static WordSpecification get() {
		return new WordSpecification(new EmptyFilter());
	}

	public static WordSpecification where(BusinessAttribute key, SearchOperator operation, Object value) {
		return new WordSpecification(new Filter(key, operation, value));
	}

	public static WordSpecification where(BusinessAttribute key, Object value) {
		return new WordSpecification(new Filter(key, value));
	}

}
