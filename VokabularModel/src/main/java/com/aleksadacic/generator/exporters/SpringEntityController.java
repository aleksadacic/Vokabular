package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringEntityControllerWriter;

@SuppressWarnings("unused")
public class SpringEntityController extends AbstractExporter {
    public SpringEntityController() {
        super(SpringEntityControllerWriter.class);
        setObjectSuffix("Controller");
        setOverwrite(false);
    }
}
