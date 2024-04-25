package com.aleksadacic.vokabular.postgresql.entities.word;

import com.aleksadacic.vokabular.postgresql.entities.wordtype.WordType;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.Optional;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;

@NoRepositoryBean
@SuppressWarnings("unused")
public interface WordRepositoryBase extends JpaRepository<WordEntity, String>,  JpaSpecificationExecutor <WordEntity>, DataEntityRepository<WordEntity> {
	Optional<WordEntity> findByValue(String value);
	Optional<WordEntity> findByType(WordType type);
	Optional<WordEntity> findByUsage(String usage);
	Optional<WordEntity> findByMeaning(String meaning);

}
