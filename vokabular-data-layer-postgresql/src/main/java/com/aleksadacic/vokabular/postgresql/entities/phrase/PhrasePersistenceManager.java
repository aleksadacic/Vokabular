package com.aleksadacic.vokabular.postgresql.entities.phrase;

import com.aleksadacic.vokabular.business.entities.phrase.Phrase;
import com.aleksadacic.engine.model.annotations.EntityPersistenceManager;
import org.springframework.stereotype.Component;

@Component
@EntityPersistenceManager(businessClass=Phrase.class)
public class PhrasePersistenceManager extends PhrasePersistenceManagerBase {
}
