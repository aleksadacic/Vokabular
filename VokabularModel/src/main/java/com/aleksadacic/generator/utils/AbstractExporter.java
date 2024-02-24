package com.aleksadacic.generator.utils;

import com.aleksadacic.creator.languages.java.JavaOutputFile;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.writer.Exporter;
import com.aleksadacic.creator.turbo.writer.elements.OutputElement;
import com.aleksadacic.creator.turbo.writer.file.OutputFile;
import com.aleksadacic.creator.turbo.writer.file.Writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractExporter implements Exporter {
    private final Map<String, Object> values = new HashMap<>();
    private final Class<? extends Writer> writerClass;
    private final String level;

    @SuppressWarnings("java:S5993")
    public AbstractExporter(Class<? extends Writer> writerClass, String level) {
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
    public void export(List<ModelObject> modelObjects, String path) throws Exception {
        for (ModelObject modelObject : modelObjects) {
            String fullPathName = WriterUtils.createPath(path, modelObject.getName().toLowerCase());
            String fullPackageName = WriterUtils.getPackageFromPath(modelObject.getName(), level);

            Writer writer = WriterUtils.getWriter(modelObject, fullPackageName, writerClass);
            List<OutputElement> outputElements = writer.export();
            OutputFile outputFile = new JavaOutputFile(outputElements, modelObject.getName() + values.get("suffix"), fullPathName);
            outputFile.fileWrite((boolean) values.get("overwrite"));
        }
    }
}
