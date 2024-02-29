package com.aleksadacic.vokabular.controller.entities.word;

import com.aleksadacic.vokabular.business.entities.type.WordType;
import com.aleksadacic.engine.datatypes.Id;
import jakarta.validation.constraints.*;
import com.aleksadacic.engine.utils.ConverterUtils;
import org.modelmapper.ModelMapper;
import com.aleksadacic.vokabular.business.entities.word.Word;
import com.aleksadacic.engine.framework.service.DTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@Scope("prototype")
public class WordDTO implements DTO<Word> {
	private Id id;
	private @Min(2) @Max(200) String value;
	private WordType type;
	private String usage;
	private String meaning;

	@Override
	public Word convertToBusinessEntity() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper
				.typeMap(WordDTO.class, Word.class)
				.addMappings(mapper -> mapper.using(ConverterUtils.STRING_TO_ID).map(WordDTO::getId, Word::setId));
		return modelMapper.map(this, Word.class);
	}
}
