package com.aleksadacic.vokabular.vokabulardataimport.importers;

import com.aleksadacic.engine.dataimport.EntityImporter;
import com.aleksadacic.engine.dataimport.ImportData;
import com.aleksadacic.engine.dataimport.ImportRow;
import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.framework.business.BusinessManager;
import com.aleksadacic.vokabular.business.entities.word.Word;
import com.aleksadacic.vokabular.business.entities.word.WordManager;

import java.io.InputStream;

public class WordImporter implements EntityImporter<Word> {
    private final WordManager manager;
    private final InputStream file;

    public WordImporter(WordManager manager, InputStream file) {
        this.manager = manager;
        this.file = file;
    }

    @Override
    public void performPersistenceOperation(Word entity) throws TurboException {
        manager.insert(entity);
    }

    @Override
    public void validatePersistenceOperation(Word entity) throws TurboException {
        entity.validateInsert();
        entity.validateSave();
    }

    @Override
    public void transformData(ImportData data) {
        // empty
    }

    @Override
    public Word createTargetEntity(BusinessManager<Word> manager, ImportRow row) throws TurboException {
        return manager.create();
    }

    @Override
    public WordManager getBusinessManager() {
        return manager;
    }

    @Override
    public Class<Word> getEntityClass() {
        return Word.class;
    }

    @Override
    public InputStream getFile() {
        return file;
    }
}
