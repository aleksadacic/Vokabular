package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.writers.SpringControllerEntityDtoWriter;

public class SpringControllerEntityDto extends AbstractExporter {

    public SpringControllerEntityDto() {
        super(SpringControllerEntityDtoWriter.class);
        setObjectSuffix("DTO");
        setOverwrite(true);
    }
}
