package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringEntityAttributeWriter;

@SuppressWarnings("unused")
public class SpringEntityAttribute extends AbstractExporter {
    public SpringEntityAttribute() {
        super(SpringEntityAttributeWriter.class, AppLevel.BUSINESS.getName());
        setObjectSuffix("Attribute");
        setOverwrite(true);
    }
}
