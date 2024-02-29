package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractEnumExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringBusinessEnumWriter;

@SuppressWarnings("unused")
public class SpringBusinessEnum extends AbstractEnumExporter {
    public SpringBusinessEnum() {
        super(SpringBusinessEnumWriter.class, AppLevel.BUSINESS.getName());
        setOverwrite(true);
    }
}
