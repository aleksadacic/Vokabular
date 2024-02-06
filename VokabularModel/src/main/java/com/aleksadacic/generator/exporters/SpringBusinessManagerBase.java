package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringBusinessManagerBaseWriter;

@SuppressWarnings("unused")
public class SpringBusinessManagerBase extends AbstractExporter {

    public SpringBusinessManagerBase() {
        super(SpringBusinessManagerBaseWriter.class);
        setObjectSuffix("ManagerBase");
    }
}
