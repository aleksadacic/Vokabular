package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringPersistenceRepositoryBaseWriter;
import com.aleksadacic.generator.writers.SpringPersistenceRepositoryWriter;

@SuppressWarnings("unused")
public class SpringPersistenceRepositoryBase extends AbstractExporter {
    public SpringPersistenceRepositoryBase() {
        super(SpringPersistenceRepositoryBaseWriter.class);
        setOverwrite(true);
    }
}
