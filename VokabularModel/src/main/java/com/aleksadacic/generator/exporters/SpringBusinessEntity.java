package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringBusinessEntityWriter;

@SuppressWarnings("unused")
public class SpringBusinessEntity extends AbstractExporter {
    public SpringBusinessEntity() {
        super(SpringBusinessEntityWriter.class, AppLevel.BUSINESS.getName());
        setOverwrite(false);
    }
}
