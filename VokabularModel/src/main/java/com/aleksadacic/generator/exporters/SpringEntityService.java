package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringEntityServiceWriter;

@SuppressWarnings("unused")
public class SpringEntityService extends AbstractExporter {
    public SpringEntityService() {
        super(SpringEntityServiceWriter.class);
        setObjectSuffix("Service");
        setOverwrite(true);
    }
}
