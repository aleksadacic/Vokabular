package com.aleksadacic.vokabular.controller.entities.word;

import com.aleksadacic.engine.framework.service.DTO;
import com.aleksadacic.engine.utils.ConverterUtils;
import com.aleksadacic.vokabular.business.entities.word.Word;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("prototype")
public class WordDTO implements DTO<Word> {
    private String id;
    private @Min(2) @Max(200) String value;
    private String type;
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
