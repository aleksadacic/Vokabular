package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.utils.JavaUtils;
import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.exceptions.TypeNotFoundException;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.reader.ModelObjectAttribute;
import com.aleksadacic.creator.turbo.utils.TypeDefinition;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.utils.StringUtils;
import com.aleksadacic.generator.utils.WriterUtils;

import java.util.Objects;

public class SpringBusinessEntityBaseWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringBusinessEntityBaseWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        addImport("lombok.Data");
        addImport("lombok.EqualsAndHashCode");
        addImport(BusinessEntity.class);
        addImport(TurboException.class);
        addImport(Objects.class);
        addImport(Id.class);
        addImport("com.aleksadacic.engine.validations.*");
        append(0, "@Data");
        append(0, "@EqualsAndHashCode(callSuper = true)");
        append(0, "abstract class " + modelObject.getName() + "Base extends BusinessEntity {");
    }

    @Override
    public void writeClassContent() {
        append(1, "protected static final String MODEL_TYPE = \"" + modelObject.getTableName() + "\";");
        appendFields();
        appendBlankLine();
        append(1, "@Override");
        append(1, "public void validateSave() throws TurboException {");
        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            if (attribute.getType().equals("primaryKey")) {
                continue;
            }
            if (!attribute.isNullable()) {
                append(2, "if (" + attribute.getName() + " == null) {");
                append(3, "throw new NonNullableException();");
                append(2, "}");
            }
            if (attribute.getMinLength() != null) {
                append(2, "if (" + attribute.getName() + ".length() < " + attribute.getMinLength() + ") {");
                append(3, "throw new AttributeLengthException();");
                append(2, "}");
            }
            if (attribute.getMaxLength() != null) {
                append(2, "if (" + attribute.getName() + ".length() > " + attribute.getMaxLength() + ") {");
                append(3, "throw new AttributeLengthException();");
                append(2, "}");
            }
        }
        append(2, "super.validateSave();");
        append(1, "}");
        appendBlankLine();
        append(1, "@Override");
        append(1, "public void validateUpdate() throws TurboException {");
        append(2, "if (id == null) {");
        append(3, "throw new NonNullableException();");
        append(2, "}");
        append(2, "super.validateInsert();");
        append(1, "}");
        appendBlankLine();
        append(1, "@Override");
        append(1, "public void set(String name, Object value) {");
        append(2, modelObject.getName() + "Attribute attribute = (" + modelObject.getName() + "Attribute) " + modelObject.getName() + "Attribute.getByName(name);");
        append(2, "switch (Objects.requireNonNull(attribute)) {");
        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            if (attribute.getType().equals("primaryKey")) {
                append(3, "case " + attribute.getName().toUpperCase() + " -> set" + StringUtils.capitalize(attribute.getName()) + "(Id.of(value));");
            } else if (attribute.isEnumType()) {
                append(3, "case " + attribute.getName().toUpperCase() + " -> set" + StringUtils.capitalize(attribute.getName()) + "(" + attribute.getType() + ".valueOf((String) value));");
            } else {
                try {
                    append(3, "case " + attribute.getName().toUpperCase() + " -> set" + StringUtils.capitalize(attribute.getName()) + "((" + JavaUtils.getJavaType(attribute) + ") value);");
                } catch (TypeNotFoundException | IllegalArgumentException e) {
                    append(3, "case " + attribute.getName().toUpperCase() + " -> set" + StringUtils.capitalize(attribute.getName()) + "((" + attribute.getType() + ") value);");
                }
            }
        }
        append(3, "default -> super.set(name, value);");
        append(2, "}");
        append(1, "}");
        appendBlankLine();
        append(1, "@Override");
        append(1, "public Object get(String name) {");
        append(2, modelObject.getName() + "Attribute attribute = (" + modelObject.getName() + "Attribute) " + modelObject.getName() + "Attribute.getByName(name);");
        append(2, "if (attribute == null) {");
        append(3, "return super.get(name);");
        append(2, "}");
        append(2, "return switch (attribute) {");
        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            append(3, "case " + attribute.getName().toUpperCase() + " -> get" + StringUtils.capitalize(attribute.getName()) + "();");
        }
        append(2, "};");
        append(1, "}");

        append(0, "}");
    }

    private void appendFields() {
        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            try {
                append(1, "private " + JavaUtils.getJavaType(TypeDefinition.valueOf(attribute.getType().toUpperCase())) + " " + attribute.getName() + ";");
            } catch (TypeNotFoundException | IllegalArgumentException e) {
                if (!attribute.getType().equals("primaryKey")) {
                    addImport(WriterUtils.BUSINESS_PACKAGE + "." + attribute.getType().toLowerCase() + "." + attribute.getType());
                    append(1, "private " + attribute.getType() + " " + StringUtils.decapitalize(attribute.getName()) + ";");
                }
            }
        }
    }
}
