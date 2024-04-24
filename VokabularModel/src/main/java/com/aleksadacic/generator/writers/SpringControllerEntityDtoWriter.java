package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.utils.JavaUtils;
import com.aleksadacic.creator.turbo.exceptions.TypeNotFoundException;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.reader.ModelObjectAttribute;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.service.DTO;
import com.aleksadacic.engine.utils.ConverterUtils;
import com.aleksadacic.engine.utils.StringUtils;
import com.aleksadacic.generator.utils.AbstractWriter;
import com.aleksadacic.generator.utils.WriterUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * "@NotNull" validates that the annotated property value isn’t null.
 * "@AssertTrue" validates that the annotated property value is true.
 * "@Size" validates that the annotated property value has a size between the attributes min and max. We can apply it to String, Collection, Map, and array properties.
 * "@Min" validates that the annotated property has a value no smaller than the value attribute.
 * "@Max" validates that the annotated property has a value no larger than the value attribute.
 * "@Email" validates that the annotated property is a valid email address.
 * <p>
 * Some annotations accept additional attributes, but the message attribute is common to all of them. This is the message that will usually be rendered when the value of the respective property fails validation.
 * <p>
 * Here are some additional annotations we can find in the JSR:
 * "@NotEmpty" validates that the property isn’t null or empty. We can apply it to String, Collection, Map or Array values.
 * "@NotBlank" can be applied only to text values, and validates that the property isn’t null or whitespace.
 * "@Positive" and @PositiveOrZero apply to numeric values, and validate that they’re strictly positive, or positive including 0.
 * "@Negative" and @NegativeOrZero apply to numeric values, and validate that they’re strictly negative, or negative including 0.
 * "@Past" and @PastOrPresent validate that a date value is in the past, or the past including the present. We can apply it to date types, including those added in Java 8.
 * "@Future" and @FutureOrPresent validate that a date value is in the future, or in the future including the present.
 */
public class SpringControllerEntityDtoWriter extends AbstractWriter {
    public SpringControllerEntityDtoWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport("lombok.Data");
        addImport("org.springframework.stereotype.Component");
        addImport("org.springframework.context.annotation.Scope");
        addImport(DTO.class);
        addImport(WriterUtils.BUSINESS_PACKAGE + "." + modelObject.getName().toLowerCase() + "." + modelObject.getName());
        addImport("org.modelmapper.ModelMapper");
        addImport(ConverterUtils.class);

        append(0, "@Data");
        append(0, "@Component");
        append(0, "@Scope(\"prototype\")");
        append(0, "public class " + modelObject.getName() + "DTO implements DTO<" + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        addImport("jakarta.validation.constraints.*");

        boolean hasPrimaryKey = false;

        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            if (attribute.getType().equals("primaryKey")) {
                addImport(Id.class.getName());
                append(1, "private Id " + attribute.getName() + ";");
                hasPrimaryKey = true;
                continue;
            }
            StringBuilder field = new StringBuilder("private ");
            appendValidations(attribute, field);

            try {
                field.append(JavaUtils.getJavaType(attribute)).append(" ").append(attribute.getName()).append(";");
            } catch (TypeNotFoundException | IllegalArgumentException e) {
                addImport(WriterUtils.BUSINESS_PACKAGE + "." + attribute.getType().toLowerCase() + "." + attribute.getType());
                field.append(attribute.getType()).append(" ").append(StringUtils.decapitalize(attribute.getName())).append(";");
            }
            append(1, field.toString());
        }
        if (hasPrimaryKey) {
            appendBlankLine();
            append(1, "@Override");
            append(1, "public " + modelObject.getName() + " convertToBusinessEntity() {");
            append(2, "ModelMapper modelMapper = new ModelMapper();");
            append(2, "modelMapper");
            append(4, ".typeMap(" + modelObject.getName() + "DTO.class, " + modelObject.getName() + ".class)");
            append(4, ".addMappings(mapper -> mapper.using(ConverterUtils.STRING_TO_ID).map(" + modelObject.getName() + "DTO::getId, " + modelObject.getName() + "::setId));");
            append(2, "return modelMapper.map(this, " + modelObject.getName() + ".class);");
            append(1, "}");
        }
        append(0, "}");
    }


    /**
     * @see SpringControllerEntityDtoWriter
     */
    //    TODO jace validacije i message
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
