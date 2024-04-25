package com.aleksadacic.vokabular.postgresql.entities.appuser;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.persistence.AbstractPersistenceManager;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import com.aleksadacic.engine.user.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppUserPersistenceManagerBase extends AbstractPersistenceManager<AppUser, AppUserEntity> {
    @Autowired
    private AppUserRepository repo;

    protected AppUserPersistenceManagerBase() {
        super(com.aleksadacic.engine.user.AppUser.class, AppUserEntity.class);
    }

    @Override
    protected DataEntityRepository<AppUserEntity> getRepository() {
        return repo;
    }

    @Override
    protected AppUser convertToBusinessEntity(AppUserEntity source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .typeMap(AppUserEntity.class, AppUser.class)
                .addMappings(mapper -> mapper.map(AppUserEntity::getId, (destination, value) -> destination.setId(Id.of(value))));
        return modelMapper.map(source, AppUser.class);
    }

    @Override
    protected AppUserEntity convertToPersistenceEntity(AppUser source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .typeMap(AppUser.class, AppUserEntity.class)
                .addMappings(mapper -> mapper.map(source1 -> source1.getId().getValue(), (destination, value) -> destination.setId((String) value)));
        return modelMapper.map(source, AppUserEntity.class);
    }
}
