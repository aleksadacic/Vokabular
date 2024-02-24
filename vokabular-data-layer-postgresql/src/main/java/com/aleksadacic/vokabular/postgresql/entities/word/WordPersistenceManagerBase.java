package com.aleksadacic.vokabular.postgresql.entities.word;

import com.aleksadacic.vokabular.postgresql.AbstractPersistenceManager;
import com.aleksadacic.engine.utils.ConverterUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;

@Component
public class WordPersistenceManagerBase extends AbstractPersistenceManager<com.aleksadacic.vokabular.business.entities.word.Word, Word> {
    @Autowired
    private WordRepository repository;

    protected WordPersistenceManagerBase() {
        super(com.aleksadacic.vokabular.business.entities.word.Word.class, Word.class);
    }

    @Override
    protected DataEntityRepository<Word> getRepository() {
        return repository;
    }

    @Override
    protected com.aleksadacic.vokabular.business.entities.word.Word convertToBusinessEntity(Word source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .typeMap(Word.class, com.aleksadacic.vokabular.business.entities.word.Word.class)
                .addMappings(mapper -> mapper.using(ConverterUtils.STRING_TO_ID).map(Word::getId, com.aleksadacic.vokabular.business.entities.word.Word::setId));
        return modelMapper.map(source, com.aleksadacic.vokabular.business.entities.word.Word.class);
    }

    @Override
    protected Word convertToPersistenceEntity(com.aleksadacic.vokabular.business.entities.word.Word source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .typeMap(com.aleksadacic.vokabular.business.entities.word.Word.class, Word.class)
                .addMappings(mapper -> mapper.map(source1 -> source1.getId().getValue(), Word::setId));
        return modelMapper.map(source, Word.class);
    }
}
