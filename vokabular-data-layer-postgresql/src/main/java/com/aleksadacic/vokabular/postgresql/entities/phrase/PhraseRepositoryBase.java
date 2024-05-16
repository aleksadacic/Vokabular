package com.aleksadacic.vokabular.postgresql.entities.phrase;

import org.springframework.data.repository.NoRepositoryBean;
import java.util.Optional;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;

@NoRepositoryBean
@SuppressWarnings("unused")
public interface PhraseRepositoryBase extends JpaRepository<PhraseEntity, String>,  JpaSpecificationExecutor <PhraseEntity>, DataEntityRepository<PhraseEntity> {
	Optional<PhraseEntity> findByValue(String value);

}
