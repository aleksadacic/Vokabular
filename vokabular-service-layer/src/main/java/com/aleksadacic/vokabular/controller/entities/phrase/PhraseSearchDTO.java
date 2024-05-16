package com.aleksadacic.vokabular.controller.entities.phrase;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import com.aleksadacic.vokabular.business.entities.phrase.PhraseSpecification;
import com.aleksadacic.vokabular.business.entities.phrase.PhraseAttribute;
import com.aleksadacic.engine.framework.service.SearchFilter;
import com.aleksadacic.engine.framework.service.SearchDTO;

@Component
@Scope("prototype")
public class PhraseSearchDTO extends SearchDTO {
	@Override
	@SuppressWarnings("unchecked")
	public PhraseSpecification buildSpecification() {
		PhraseSpecification specification = PhraseSpecification.get();
		for (SearchFilter filter : getFilters()) {
			specification.and(PhraseAttribute.getByName(filter.getKey()), filter.getOperator(), filter.getValue());
		}
		return specification;
	}
}
