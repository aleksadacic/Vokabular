package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringEntitySpecificationWriter;

@SuppressWarnings("unused")
public class SpringEntitySpecification extends AbstractExporter {
    public SpringEntitySpecification() {
        super(SpringEntitySpecificationWriter.class, AppLevel.BUSINESS.getName());
        setObjectSuffix("Specification");
        setOverwrite(false);
    }
}
