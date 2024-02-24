package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.business.BusinessManager;

public class SpringBusinessManagerBaseWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringBusinessManagerBaseWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        addImport(BusinessManager.class);
        addImport("com.aleksadacic.vokabular.business.AbstractBusinessManager");
        append(0, "abstract class " + modelObject.getName() + "ManagerBase extends AbstractBusinessManager<" + modelObject.getName() + "> implements BusinessManager<" + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        append(1, "protected " + modelObject.getName() + "ManagerBase() {}");
        appendBlankLine();
        append(1, "@Override");
        append(1, "public Class<" + modelObject.getName() + "> getEntityClass() {");
        append(2, "return " + modelObject.getName() + ".class;");
        append(1, "}");
        append(0, "}");
    }
}
