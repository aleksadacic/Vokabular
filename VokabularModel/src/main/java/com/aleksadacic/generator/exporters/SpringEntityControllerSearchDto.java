package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringEntityControllerSearchDtoWriter;

@SuppressWarnings("unused")
public class SpringEntityControllerSearchDto extends AbstractExporter {
    public SpringEntityControllerSearchDto() {
        super(SpringEntityControllerSearchDtoWriter.class, AppLevel.SERVICE.getName());
        setObjectSuffix("SearchDTO");
        setOverwrite(false);
    }
}
