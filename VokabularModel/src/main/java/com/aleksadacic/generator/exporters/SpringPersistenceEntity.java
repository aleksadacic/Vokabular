package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringPersistenceEntityWriter;

@SuppressWarnings("unused")
public class SpringPersistenceEntity extends AbstractExporter {
    public SpringPersistenceEntity() {
        super(SpringPersistenceEntityWriter.class, AppLevel.DATA.getName());
        setOverwrite(true);
        setObjectSuffix("Entity");
    }
}
