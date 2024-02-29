package com.aleksadacic;

import com.aleksadacic.config.TurboCreatorConfig;
import com.aleksadacic.creator.TurboCreator;
import com.aleksadacic.creator.turbo.writer.Exporter;
import com.aleksadacic.generator.utils.ExporterPojo;
import com.aleksadacic.model.export.ModelExporter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TurboExportAndCreate {
    public static void main(String[] args) throws Exception {
        File model = ModelExporter.export(TurboCreatorConfig.getSourcePackage(), TurboCreatorConfig.getExportFilePath());

        List<ExporterPojo> exporterDefinition = readExporterDefinition();

        for (ExporterPojo item : exporterDefinition) {
            TurboCreator turboCreator = new TurboCreator(item.getOutputPath());
            turboCreator.generate(getExporters(item), model);
        }
    }

    private static List<Exporter> getExporters(ExporterPojo item) throws Exception {
        List<Exporter> exporters = new ArrayList<>();
        System.out.println(item.getName() + ": ");
        for (String exporterName : item.getExporters()) {
            System.out.println(exporterName);
            Class<?> exporterClass = Class.forName(exporterName);
            Exporter exporter = (Exporter) exporterClass.getConstructor().newInstance();
            exporters.add(exporter);
        }
        System.out.println();
        return exporters;
    }

    private static List<ExporterPojo> readExporterDefinition() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(new File(TurboExportAndCreate.class.getClassLoader().getResource("exporters.json").getFile()), ExporterPojo[].class).clone());
    }
}
