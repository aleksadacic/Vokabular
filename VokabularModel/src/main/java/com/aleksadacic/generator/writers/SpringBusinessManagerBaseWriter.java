package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.utils.JavaUtils;
import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.exceptions.TypeNotFoundException;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.reader.ModelObjectAttribute;
import com.aleksadacic.creator.turbo.utils.TypeDefinition;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.AttributeDefinition;
import com.aleksadacic.engine.framework.business.AbstractBusinessManager;
import com.aleksadacic.generator.utils.WriterUtils;

import java.util.ArrayList;
import java.util.List;

public class SpringBusinessManagerBaseWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringBusinessManagerBaseWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        addImport(AbstractBusinessManager.class);
        append(0, "abstract class " + modelObject.getName() + "ManagerBase extends AbstractBusinessManager<" + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        append(1, "private static final List<AttributeDefinition> definitions = new ArrayList<>();");
        appendBlankLine();
        addImport("com.aleksadacic.vokabular.business.SpringContext");
        append(1, "protected " + modelObject.getName() + "ManagerBase() {");
        append(2, "super(SpringContext.getCurrentUserObject());");
        append(1, "}");
        appendBlankLine();
        append(1, "static {");
        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            try {
                String type = JavaUtils.getJavaType(TypeDefinition.valueOf(attribute.getType().toUpperCase()));
                append(2, "definitions.add(new AttributeDefinition(\"" + attribute.getName() + "\", " + type + ".class, " + attribute.isNullable() + "));");
            } catch (TypeNotFoundException | IllegalArgumentException e) {
                if (attribute.getType().equals("primaryKey")) {
                    String type = "Id";
                    addImport(Id.class);
                    append(2, "definitions.add(new AttributeDefinition(\"" + attribute.getName() + "\", " + type + ".class, " + attribute.isNullable() + "));");
                } else {
                    addImport(WriterUtils.BUSINESS_PACKAGE + "." + attribute.getType().toLowerCase() + "." + attribute.getType());
                    append(2, "definitions.add(new AttributeDefinition(\"" + attribute.getName() + "\", " + attribute.getType() + ".class, " + attribute.isNullable() + "));");
                }
            }
        }
        append(1, "}");
        appendBlankLine();
        append(1, "@Override");
        append(1, "public Class<" + modelObject.getName() + "> getEntityClass() {");
        append(2, "return " + modelObject.getName() + ".class;");
        append(1, "}");

        appendBlankLine();
        addImport(List.class);
        addImport(ArrayList.class);
        addImport(AttributeDefinition.class);
        append(1, "@Override");
        append(1, "public List<AttributeDefinition> getAttributeDefinitions() {");
        append(2, "return definitions;");
        append(1, "}");
        append(0, "}");
    }
}
