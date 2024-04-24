package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringPersistenceRepositoryBaseWriter;

@SuppressWarnings("unused")
public class SpringPersistenceRepositoryBase extends AbstractExporter {
    public SpringPersistenceRepositoryBase() {
        super(SpringPersistenceRepositoryBaseWriter.class, AppLevel.DATA.getName());
        setObjectSuffix("RepositoryBase");
        setOverwrite(true);
    }
}
