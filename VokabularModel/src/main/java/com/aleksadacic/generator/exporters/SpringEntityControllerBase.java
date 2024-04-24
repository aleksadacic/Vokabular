package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringEntityControllerBaseWriter;

@SuppressWarnings("unused")
public class SpringEntityControllerBase extends AbstractExporter {
    public SpringEntityControllerBase() {
        super(SpringEntityControllerBaseWriter.class, AppLevel.SERVICE.getName());
        setObjectSuffix("ControllerBase");
        setOverwrite(true);
    }
}
