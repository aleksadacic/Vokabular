package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringPersistenceRepositoryWriter;

@SuppressWarnings("unused")
public class SpringPersistenceRepository extends AbstractExporter {
    public SpringPersistenceRepository() {
        super(SpringPersistenceRepositoryWriter.class);
        setOverwrite(true);
    }
}
