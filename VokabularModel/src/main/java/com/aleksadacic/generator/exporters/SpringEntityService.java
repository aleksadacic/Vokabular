package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringEntityServiceWriter;

@SuppressWarnings("unused")
public class SpringEntityService extends AbstractExporter {
    public SpringEntityService() {
        super(SpringEntityServiceWriter.class, AppLevel.SERVICE.getName());
        setObjectSuffix("Service");
        setOverwrite(true);
    }
}
