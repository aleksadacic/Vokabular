package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.utils.JavaUtils;
import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.exceptions.TypeNotFoundException;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.reader.ModelObjectAttribute;
import com.aleksadacic.creator.turbo.utils.TypeDefinition;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import com.aleksadacic.engine.utils.StringUtils;
import com.aleksadacic.generator.utils.WriterUtils;

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
        append(0, "@Data");
        append(0, "@EqualsAndHashCode(callSuper = true)");
        append(0, "abstract class " + modelObject.getName() + "Base extends BusinessEntity {");
    }

    @Override
    public void writeClassContent() {
        append(1, "protected static final String MODEL_TYPE = \"" + modelObject.getTableName() + "\";");
        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            try {
                append(1, "private " + JavaUtils.getJavaType(TypeDefinition.valueOf(attribute.getType().toUpperCase())) + " " + attribute.getName() + ";");
            } catch (TypeNotFoundException | IllegalArgumentException e) {
                if (attribute.getType().equals("primaryKey")) {
                    // skip, we have it in abstract business class
                } else {
                    addImport(WriterUtils.BUSINESS_PACKAGE + "." + attribute.getName() + "." + attribute.getType());
                    append(1, "private " + attribute.getType() + " " + StringUtils.decapitalize(attribute.getName()) + ";");
                }
            }
        }

        append(0, "}");
    }
}
