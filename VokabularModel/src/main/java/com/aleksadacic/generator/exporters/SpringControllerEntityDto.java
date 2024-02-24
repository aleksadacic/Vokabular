package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringControllerEntityDtoWriter;

@SuppressWarnings("unused")
public class SpringControllerEntityDto extends AbstractExporter {
    public SpringControllerEntityDto() {
        super(SpringControllerEntityDtoWriter.class, AppLevel.SERVICE.getName());
        setObjectSuffix("DTO");
        setOverwrite(true);
    }
}
