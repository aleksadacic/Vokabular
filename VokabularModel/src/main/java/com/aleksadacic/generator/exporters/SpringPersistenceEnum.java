package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractEnumExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringPersistenceEnumWriter;

@SuppressWarnings("unused")
public class SpringPersistenceEnum extends AbstractEnumExporter {
    public SpringPersistenceEnum() {
        super(SpringPersistenceEnumWriter.class, AppLevel.DATA.getName());
        setOverwrite(true);
    }
}
