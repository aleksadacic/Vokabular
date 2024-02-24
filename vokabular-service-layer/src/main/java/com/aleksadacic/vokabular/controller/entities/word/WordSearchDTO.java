package com.aleksadacic.vokabular.controller.entities.word;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import com.aleksadacic.vokabular.business.entities.word.WordSpecification;
import com.aleksadacic.vokabular.business.entities.word.WordAttribute;
import com.aleksadacic.engine.framework.service.SearchFilter;
import com.aleksadacic.engine.framework.service.SearchDTO;

@Component
@Scope("prototype")
public class WordSearchDTO extends SearchDTO {
	@Override
	@SuppressWarnings("unchecked")
	public WordSpecification buildSpecification() {
		WordSpecification specification = WordSpecification.get();
		for (SearchFilter filter : getFilters()) {
			specification.and(WordAttribute.getByName(filter.getKey()), filter.getOperator(), filter.getValue());
		}
		return specification;
	}
}
