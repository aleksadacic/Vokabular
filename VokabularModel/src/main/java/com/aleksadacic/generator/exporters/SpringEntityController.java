package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringEntityControllerWriter;

@SuppressWarnings("unused")
public class SpringEntityController extends AbstractExporter {
    public SpringEntityController() {
        super(SpringEntityControllerWriter.class, AppLevel.SERVICE.getName());
        setObjectSuffix("Controller");
        setOverwrite(false);
    }
}
