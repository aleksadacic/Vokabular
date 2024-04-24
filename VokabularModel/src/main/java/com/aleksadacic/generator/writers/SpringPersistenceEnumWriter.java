package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelEnumObject;
import com.aleksadacic.creator.turbo.reader.ModelEnumObjectAttribute;
import org.springframework.util.StringUtils;

public class SpringPersistenceEnumWriter extends JavaClassWriter {
    private final ModelEnumObject modelObject;

    public SpringPersistenceEnumWriter(ModelEnumObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        append(0, "public enum " + modelObject.getName() + " {");
    }

    @Override
    public void writeClassContent() {
        for (ModelEnumObjectAttribute attribute : modelObject.getAttributes()) {
            if (!StringUtils.hasLength(attribute.getValue())) {
                append(1, attribute.getName() + "(\"" + attribute.getDefaultValue() + "\"),");
            }
            append(1, attribute.getName() + "(\"" + attribute.getValue() + "\"),");
        }
        append(1, ";");
        appendBlankLine();
        append(1, "private final String value;");
        append(1, modelObject.getName() + "(String value) {");
        append(2, "this.value = value;");
        append(1, "}");
        appendBlankLine();
        append(1, "public String getValue() {");
        append(2, "return value;");
        append(1, "}");
        append(0, "}");
    }
}
