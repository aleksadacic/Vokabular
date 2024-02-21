package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringEntitySpecificationBaseWriter;

@SuppressWarnings("unused")
public class SpringEntitySpecificationBase extends AbstractExporter {
    public SpringEntitySpecificationBase() {
        super(SpringEntitySpecificationBaseWriter.class);
        setOverwrite(true);
    }
}
