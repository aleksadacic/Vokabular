package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class SpringBusinessManagerWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringBusinessManagerWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        addImport(Component.class);
        addImport(Scope.class);
        append(0, "@Component");
        append(0, "@Scope(\"prototype\")");
        append(0, "public class " + modelObject.getName() + "Manager extends " + modelObject.getName() + "ManagerBase {");
    }

    @Override
    public void writeClassContent() {
        append(0, "}");
    }
}
