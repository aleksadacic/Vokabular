package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.utils.JavaUtils;
import com.aleksadacic.creator.turbo.exceptions.TypeNotFoundException;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.reader.ModelObjectAttribute;
import com.aleksadacic.creator.turbo.utils.TypeDefinition;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.persistence.PersistenceEntity;
import com.aleksadacic.engine.utils.StringUtils;
import com.aleksadacic.generator.utils.AbstractWriter;
import com.aleksadacic.generator.utils.WriterUtils;

public class SpringPersistenceEntityWriter extends AbstractWriter {
    public SpringPersistenceEntityWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport(PersistenceEntity.class);
        addImport("jakarta.persistence.*");
        addImport("lombok.Data");
        addImport("lombok.NoArgsConstructor");
        addImport("lombok.AllArgsConstructor");

        append(0, "@Entity");
        append(0, "@Data");
        append(0, "@Table(name = \"" + modelObject.getTableName() + "\")");
        append(0, "@NoArgsConstructor");
        append(0, "@AllArgsConstructor");
        append(0, "public class " + modelObject.getName() + " implements PersistenceEntity {");
    }

    @Override
    public void writeClassContent() {
        append(1, "@Id");
        append(1, "@GeneratedValue(strategy = GenerationType.UUID)");
        append(1, "private String id;");

        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            try {
                append(1, "private " + JavaUtils.getJavaType(TypeDefinition.valueOf(attribute.getType().toUpperCase())) + " " + attribute.getName() + ";");
            } catch (TypeNotFoundException | IllegalArgumentException e) {
                if (!attribute.getType().equals("primaryKey")) {
                    addImport(WriterUtils.DATA_PACKAGE + "." + attribute.getName() + "." + attribute.getType());
                    append(1, "private " + attribute.getType() + " " + StringUtils.decapitalize(attribute.getName()) + ";");
                }
            }
        }
        appendBlankLine();

        append(1, "public void setId (String id){");
        append(2, "this.id = id;");
        append(1, "}");

        append(0, "}");
    }
}
