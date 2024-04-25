package com.aleksadacic.vokabular.postgresql.entities.word;

import com.aleksadacic.vokabular.business.entities.word.Word;
import com.aleksadacic.engine.model.annotations.EntityPersistenceManager;
import org.springframework.stereotype.Component;

@Component
@EntityPersistenceManager(businessClass=Word.class)
public class WordPersistenceManager extends WordPersistenceManagerBase {
}
