package com.aleksadacic.generator.utils;

import com.aleksadacic.creator.languages.java.JavaOutputFile;
import com.aleksadacic.creator.turbo.reader.ExportObject;
import com.aleksadacic.creator.turbo.reader.ModelEnumObject;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.writer.Exporter;
import com.aleksadacic.creator.turbo.writer.ExporterType;
import com.aleksadacic.creator.turbo.writer.elements.OutputElement;
import com.aleksadacic.creator.turbo.writer.file.OutputFile;
import com.aleksadacic.creator.turbo.writer.file.Writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractEnumExporter implements Exporter {
    private final Map<String, Object> values = new HashMap<>();
    private final Class<? extends Writer> writerClass;
    private final String level;

    @Override
    public ExporterType getType() {
        return ExporterType.ENUM;
    }

    @SuppressWarnings("java:S5993")
    public AbstractEnumExporter(Class<? extends Writer> writerClass, String level) {
        this.writerClass = writerClass;
        this.level = level;
        initDefaults();
    }

    protected void setObjectSuffix(String suffix) {
        values.put("suffix", suffix);
    }

    protected void setOverwrite(boolean overwrite) {
        values.put("overwrite", overwrite);
    }


    private void initDefaults() {
        values.put("overwrite", true);
        values.put("suffix", "");
    }

    @Override
    public void export(List<ExportObject> modelObjects, String path) throws Exception {
        for (ExportObject modelObject : modelObjects) {
            String fullPathName = WriterUtils.createPath(path, ((ModelEnumObject) modelObject).getName().toLowerCase());
            String fullPackageName = WriterUtils.getPackageFromPath(((ModelEnumObject) modelObject).getName(), level).replaceAll("entities", "enums");

            Writer writer = WriterUtils.getWriter(((ModelEnumObject) modelObject), fullPackageName, writerClass);
            List<OutputElement> outputElements = writer.export();
            OutputFile outputFile = new JavaOutputFile(outputElements, ((ModelEnumObject) modelObject).getName() + values.get("suffix"), fullPathName);
            outputFile.fileWrite((boolean) values.get("overwrite"));
        }
    }
}
