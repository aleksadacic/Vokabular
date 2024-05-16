package com.aleksadacic.vokabular.postgresql.entities.phrase;

import com.aleksadacic.vokabular.business.entities.phrase.Phrase;
import com.aleksadacic.engine.framework.persistence.AbstractPersistenceManager;
import com.aleksadacic.engine.utils.ConverterUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;

@Component
public class PhrasePersistenceManagerBase extends AbstractPersistenceManager<Phrase, PhraseEntity> {
	@Autowired
	private PhraseRepository repository;

	protected PhrasePersistenceManagerBase() {
		super(Phrase.class, PhraseEntity.class);
	}

	@Override
	protected DataEntityRepository<PhraseEntity> getRepository() {
		return repository;
	}
	@Override
	protected Phrase convertToBusinessEntity(PhraseEntity source) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper
				.typeMap(PhraseEntity.class, Phrase.class)
				.addMappings(mapper -> mapper.using(ConverterUtils.STRING_TO_ID).map(PhraseEntity::getId, Phrase::setId));
		return modelMapper.map(source, Phrase.class);
	}
	@Override
	protected PhraseEntity convertToPersistenceEntity(Phrase source) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper
				.typeMap(Phrase.class, PhraseEntity.class)
				.addMappings(mapper -> mapper.map(source1 -> source1.getId().getValue(), PhraseEntity::setId));
		return modelMapper.map(source, PhraseEntity.class);
	}
}
