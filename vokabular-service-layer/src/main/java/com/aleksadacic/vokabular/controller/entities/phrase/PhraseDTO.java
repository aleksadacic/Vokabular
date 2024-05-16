package com.aleksadacic.vokabular.controller.entities.phrase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.aleksadacic.engine.datatypes.Id;
import jakarta.validation.constraints.*;
import com.aleksadacic.engine.utils.ConverterUtils;
import org.modelmapper.ModelMapper;
import com.aleksadacic.vokabular.business.entities.phrase.Phrase;
import com.aleksadacic.engine.framework.service.DataTransferObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@Scope("prototype")
public class PhraseDTO implements DataTransferObject<Phrase> {
	private Id id;
	private @NotEmpty @Min(10) @Max(200) String value;

	@Override
	public Phrase toBusinessEntity() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper
				.typeMap(PhraseDTO.class, Phrase.class)
				.addMappings(mapper -> mapper.using(ConverterUtils.STRING_TO_ID).map(PhraseDTO::getId, Phrase::setId));
		return modelMapper.map(this, Phrase.class);
	}

	@Override
	public String toJson() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			return id.getValue();
		}
	}
}
