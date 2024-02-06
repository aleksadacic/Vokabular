package com.aleksadacic.generator.exporters;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringEntityControllerBaseWriter;

@SuppressWarnings("unused")
public class SpringEntityControllerBase extends AbstractExporter {
    public SpringEntityControllerBase() {
        super(SpringEntityControllerBaseWriter.class);
        setObjectSuffix("ControllerBase");
        setOverwrite(true);
    }
}
