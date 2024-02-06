package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;

public class SpringBusinessEntityWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringBusinessEntityWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        append(0, "public class " + modelObject.getName() + " extends " + modelObject.getName() + "Base {");
    }

    @Override
    public void writeClassContent() {

        append(0, "}");
    }
}
