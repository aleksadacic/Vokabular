package com.aleksadacic.generator.exporters;

import com.aleksadacic.generator.utils.AbstractExporter;
import com.aleksadacic.generator.utils.AppLevel;
import com.aleksadacic.generator.writers.SpringBusinessEntityWriter;
import com.aleksadacic.generator.writers.SpringEntityImporterWriter;

@SuppressWarnings("unused")
public class SpringEntityImporter extends AbstractExporter {
    public SpringEntityImporter() {
        super(SpringEntityImporterWriter.class, AppLevel.IMPORT.getName());
        setOverwrite(false);
        setObjectSuffix("Importer");
    }
}
