package com.aleksadacic.vokabular.postgresql.entities.appuser;

import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public interface AppUserRepository extends DataEntityRepository<AppUserEntity> {
    Optional<AppUserEntity> findByUsername(String username);
}
