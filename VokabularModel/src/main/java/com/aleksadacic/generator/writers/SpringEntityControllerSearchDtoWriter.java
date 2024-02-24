package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;

public class SpringEntityControllerSearchDtoWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringEntityControllerSearchDtoWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        append(0, "@Component");
        append(0, "@Scope(\"prototype\")");
        append(0, "public class " + modelObject.getName() + "SearchDTO extends SearchDTO {");
    }

    @Override
    public void writeClassContent() {
        addImport("com.aleksadacic.engine.framework.service.SearchDTO");
        addImport("com.aleksadacic.engine.framework.service.SearchFilter");
        addImport("com.aleksadacic.vokabular.business.entities." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + "Attribute");
        addImport("com.aleksadacic.vokabular.business.entities." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + "Specification");
        addImport("org.springframework.context.annotation.Scope");
        addImport("org.springframework.stereotype.Component");
        append(1, "@Override");
        append(1, "@SuppressWarnings(\"unchecked\")");
        append(1, "public " + modelObject.getName() + "Specification buildSpecification() {");
        append(2, "" + modelObject.getName() + "Specification specification = " + modelObject.getName() + "Specification.get();");
        append(2, "for (SearchFilter filter : getFilters()) {");
        append(3, "specification.and(" + modelObject.getName() + "Attribute.getByName(filter.getKey()), filter.getOperator(), filter.getValue());");
        append(2, "}");
        append(2, "return specification;");
        append(1, "}");
        append(0, "}");
    }
}
