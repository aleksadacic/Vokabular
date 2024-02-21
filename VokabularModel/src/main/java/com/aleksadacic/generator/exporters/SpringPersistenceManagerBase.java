package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringBusinessEntityWriter;
import com.aleksadacic.generator.writers.SpringPersistenceManagerBaseWriter;

@SuppressWarnings("unused")
public class SpringPersistenceManagerBase extends AbstractExporter {
    public SpringPersistenceManagerBase() {
        super(SpringPersistenceManagerBaseWriter.class);
        setOverwrite(true);
    }
}
