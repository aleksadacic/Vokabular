package com.aleksadacic.vokabular.postgresql.entities.appuser;

import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends DataEntityRepository<AppUserEntity> {
    //    TODO generator sva polja da se ubace
    Optional<AppUserEntity> findByUsername(String username);
}
