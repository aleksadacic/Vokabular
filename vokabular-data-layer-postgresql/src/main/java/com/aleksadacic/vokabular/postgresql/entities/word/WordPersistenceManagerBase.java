package com.aleksadacic.vokabular.postgresql.entities.word;

import com.aleksadacic.vokabular.business.entities.word.Word;
import com.aleksadacic.engine.framework.persistence.AbstractPersistenceManager;
import com.aleksadacic.engine.utils.ConverterUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;

@Component
public class WordPersistenceManagerBase extends AbstractPersistenceManager<Word, WordEntity> {
	@Autowired
	private WordRepository repository;

	protected WordPersistenceManagerBase() {
		super(Word.class, WordEntity.class);
	}

	@Override
	protected DataEntityRepository<WordEntity> getRepository() {
		return repository;
	}
	@Override
	protected Word convertToBusinessEntity(WordEntity source) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper
				.typeMap(WordEntity.class, Word.class)
				.addMappings(mapper -> mapper.using(ConverterUtils.STRING_TO_ID).map(WordEntity::getId, Word::setId));
		return modelMapper.map(source, Word.class);
	}
	@Override
	protected WordEntity convertToPersistenceEntity(Word source) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper
				.typeMap(Word.class, WordEntity.class)
				.addMappings(mapper -> mapper.map(source1 -> source1.getId().getValue(), WordEntity::setId));
		return modelMapper.map(source, WordEntity.class);
	}
}
