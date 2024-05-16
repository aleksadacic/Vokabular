package com.aleksadacic.vokabular.vokabulardataimport.importers.phrase;

import java.io.InputStream;

import com.aleksadacic.vokabular.business.entities.phrase.PhraseManager;
import com.aleksadacic.vokabular.business.entities.phrase.Phrase;
import com.aleksadacic.engine.framework.business.BusinessManager;
import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.dataimport.ImportRow;
import com.aleksadacic.engine.dataimport.ImportData;
import com.aleksadacic.engine.dataimport.EntityImporter;

public class PhraseImporter implements EntityImporter<Phrase> {
    private final PhraseManager manager;
    private final InputStream file;

    public PhraseImporter(PhraseManager manager, InputStream file) {
        this.manager = manager;
        this.file = file;
    }

    @Override
    public void performPersistenceOperation(Phrase entity) throws TurboException {
        manager.insert(entity);
    }

    @Override
    public void validatePersistenceOperation(Phrase entity) throws TurboException {
        entity.validateInsert();
        entity.validateSave();
    }

    @Override
    public void transformData(ImportData data) {
        // empty
    }

    @Override
    public Phrase createTargetEntity(BusinessManager<Phrase> manager, ImportRow row) throws TurboException {
        return manager.create();
    }

    @Override
    public PhraseManager getBusinessManager() {
        return manager;
    }

    @Override
    public Class<Phrase> getEntityClass() {
        return Phrase.class;
    }

    @Override
    public InputStream getFile() {
        return file;
    }
}
