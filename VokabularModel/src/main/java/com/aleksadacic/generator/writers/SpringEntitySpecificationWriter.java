package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.querying.Filter;
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
        append(0, "public class " + modelObject.getName() + "Specification extends " + modelObject.getName() + "SpecificationBase {");
    }

    @Override
    public void writeClassContent() {
        addImport(AppUser.class);
        append(1, "public " + modelObject.getName() + "Specification(Filter filter) { super(filter); }");
        append(0, "}");
    }
}
