package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringBusinessEntityWriter;
import com.aleksadacic.generator.writers.SpringPersistenceManagerWriter;

@SuppressWarnings("unused")
public class SpringPersistenceManager extends AbstractExporter {
    public SpringPersistenceManager() {
        super(SpringPersistenceManagerWriter.class);
        setOverwrite(false);
    }
}
