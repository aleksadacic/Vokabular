package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringBusinessEntityBaseWriter;

@SuppressWarnings("unused")
public class SpringBusinessEntityBase extends AbstractExporter {
    public SpringBusinessEntityBase() {
        super(SpringBusinessEntityBaseWriter.class, AppLevel.BUSINESS.getName());
        setObjectSuffix("Base");
    }
}
