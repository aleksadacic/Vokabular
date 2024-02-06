package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.utils.JavaUtils;
import com.aleksadacic.creator.turbo.exceptions.TypeNotFoundException;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.reader.ModelObjectAttribute;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.utils.StringUtils;
import com.aleksadacic.generator.utils.AbstractWriter;
import com.aleksadacic.generator.utils.WriterUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class SpringControllerEntityDtoWriter extends AbstractWriter {
    public SpringControllerEntityDtoWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport("lombok.Data");
        addImport("org.springframework.stereotype.Component");
        addImport("org.springframework.context.annotation.Scope");

        append(0, "@Data");
        append(0, "@Component");
        append(0, "@Scope(\"prototype\")");
        append(0, "public class " + modelObject.getName() + "DTO {");
    }

    @Override
    public void writeClassContent() {
        addImport("javax.validation.constraints.*");

        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            if (attribute.getType().equals("primaryKey")) {
                addImport(Id.class.getName());
                append(1, "private Id " + attribute.getName() + ";");
                continue;
            }
            StringBuilder field = new StringBuilder("private ");
            appendValidations(attribute, field);

            try {
                field.append(JavaUtils.getJavaType(attribute)).append(" ").append(attribute.getName()).append(";");
            } catch (TypeNotFoundException | IllegalArgumentException e) {
                addImport(WriterUtils.BUSINESS_PACKAGE + "." + attribute.getName() + "." + attribute.getType());
                field.append(attribute.getType()).append(" ").append(StringUtils.decapitalize(attribute.getName())).append(";");
            }
            append(1, field.toString());
        }
        append(0, "}");
    }

    private void appendValidations(ModelObjectAttribute attribute, StringBuilder field) {
        if (canTypeHaveSize(attribute)) {
            if (!attribute.isNullable()) {
                field.append("@NotEmpty ");
            }
            if (attribute.getMinLength() != null) {
                field.append("@Min(").append(attribute.getMinLength()).append(") ");
            }
            if (attribute.getMaxLength() != null) {
                field.append("@Max(").append(attribute.getMaxLength()).append(") ");
            }
        } else {
            if (!attribute.isNullable()) {
                field.append("@NotNull ");
            }
        }
    }

    private boolean canTypeHaveSize(ModelObjectAttribute attribute) {
        try {
            Class<?> clazz = JavaUtils.getJavaTypeClass(attribute);
            return Collection.class.isAssignableFrom(clazz)
                    || Map.class.isAssignableFrom(clazz)
                    || CharSequence.class.isAssignableFrom(clazz)
                    || Array.class.isAssignableFrom(clazz);
        } catch (Exception e) {
            return false;
        }
    }
}
