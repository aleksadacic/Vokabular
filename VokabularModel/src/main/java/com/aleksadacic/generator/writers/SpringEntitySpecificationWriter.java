package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.business.BusinessAttribute;
import com.aleksadacic.engine.framework.querying.EmptyFilter;
import com.aleksadacic.engine.framework.querying.Filter;
import com.aleksadacic.engine.framework.querying.SearchOperator;
import com.aleksadacic.engine.user.AppUser;

public class SpringEntitySpecificationWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringEntitySpecificationWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        addImport(Filter.class);
        append(0, "public class " + modelObject.getName() + "Specification extends " + modelObject.getName() + "SpecificationCustom {");
    }

    @Override
    public void writeClassContent() {
        addImport(BusinessAttribute.class);
        addImport(SearchOperator.class);
        append(1, "public " + modelObject.getName() + "Specification(Filter filter) { super(filter); }");
        addImport(EmptyFilter.class);
        append(1, "public static " + modelObject.getName() + "Specification get() {");
        append(2, "return new " + modelObject.getName() + "Specification(new EmptyFilter());");
        append(1, "}");
        appendBlankLine();
        append(1, "public static " + modelObject.getName() + "Specification where(BusinessAttribute key, SearchOperator operation, Object value) {");
        append(2, "return new " + modelObject.getName() + "Specification(new Filter(key, operation, value));");
        append(1, "}");
        appendBlankLine();
        append(1, "public static " + modelObject.getName() + "Specification where(BusinessAttribute key, Object value) {");
        append(2, "return new " + modelObject.getName() + "Specification(new Filter(key, value));");
        append(1, "}");
        appendBlankLine();
        append(0, "}");
    }
}
