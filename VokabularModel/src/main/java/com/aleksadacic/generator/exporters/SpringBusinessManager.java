package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringBusinessManagerWriter;

@SuppressWarnings("unused")
public class SpringBusinessManager extends AbstractExporter {

    public SpringBusinessManager() {
        super(SpringBusinessManagerWriter.class, AppLevel.BUSINESS.getName());
        setObjectSuffix("Manager");
        setOverwrite(false);
    }
}
