package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringBusinessEntityWriter;

@SuppressWarnings("unused")
public class SpringBusinessEntity extends AbstractExporter {
    public SpringBusinessEntity() {
        super(SpringBusinessEntityWriter.class);
        setOverwrite(false);
    }
}
