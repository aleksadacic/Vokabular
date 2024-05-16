package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringEntitySpecificationCustomWriter;

@SuppressWarnings("unused")
public class SpringEntitySpecificationCustom extends AbstractExporter {
    public SpringEntitySpecificationCustom() {
        super(SpringEntitySpecificationCustomWriter.class, AppLevel.BUSINESS.getName());
        setObjectSuffix("SpecificationCustom");
        setOverwrite(false);
    }
}
