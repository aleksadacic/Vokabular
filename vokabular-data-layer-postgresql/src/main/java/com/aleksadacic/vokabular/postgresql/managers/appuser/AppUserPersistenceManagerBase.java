package com.aleksadacic.vokabular.postgresql.managers.appuser;

import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppUserPersistenceManagerBase extends AbstractPersistenceManager<com.aleksadacic.engine.user.AppUser, AppUserEntity> {
    @Autowired
    private AppUserRepository repo;

    protected AppUserPersistenceManagerBase() {
        super(com.aleksadacic.engine.user.AppUser.class, AppUserEntity.class);
    }

    @Override
    protected DataEntityRepository<AppUserEntity> getRepository() {
        return repo;
    }
}
