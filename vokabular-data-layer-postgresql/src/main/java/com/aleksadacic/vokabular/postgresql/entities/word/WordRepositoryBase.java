package com.aleksadacic.vokabular.postgresql.entities.word;

import com.aleksadacic.vokabular.postgresql.entities.type.WordType;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.Optional;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;

@NoRepositoryBean
public interface WordRepositoryBase extends JpaRepository<Word, String>,  JpaSpecificationExecutor <Word>, DataEntityRepository<Word> {
	Optional<Word> findByValue(String value);
	Optional<Word> findByType(WordType type);
	Optional<Word> findByUsage(String usage);
	Optional<Word> findByMeaning(String meaning);

}
