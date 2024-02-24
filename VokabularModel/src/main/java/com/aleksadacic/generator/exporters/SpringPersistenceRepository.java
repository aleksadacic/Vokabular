package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringPersistenceRepositoryWriter;

@SuppressWarnings("unused")
public class SpringPersistenceRepository extends AbstractExporter {
    public SpringPersistenceRepository() {
        super(SpringPersistenceRepositoryWriter.class, AppLevel.DATA.getName());
        setObjectSuffix("Repository");
        setOverwrite(false);
    }
}
