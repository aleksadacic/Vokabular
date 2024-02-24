package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.reader.ModelObjectAttribute;
import com.aleksadacic.engine.framework.business.BusinessAttribute;

public class SpringEntityAttributeWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringEntityAttributeWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        addImport(BusinessAttribute.class);
        append(0, "public enum " + modelObject.getName() + "Attribute implements BusinessAttribute {");
    }

    @Override
    public void writeClassContent() {
        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            append(1, attribute.getName().toUpperCase() + "(\"" + attribute.getName() + "\"),");
        }
        append(1, ";");
        appendBlankLine();
        append(1, "private final String name;");
        append(1, modelObject.getName() + "Attribute(String name) {");
        append(2, "this.name = name;");
        append(1, "}");
        appendBlankLine();
        append(1, "@Override");
        append(1, "public String getName() {");
        append(2, "return name;");
        append(1, "}");
        appendBlankLine();
        append(1, "public static BusinessAttribute getByName(String name) {");
        append(2, "for (BusinessAttribute attribute : values()) {");
        append(3, "if (attribute.getName().equals(name))");
        append(4, "return attribute;");
        append(3, "}");
        append(2, "return null;");
        append(1, "}");
        append(0, "}");
    }
}
