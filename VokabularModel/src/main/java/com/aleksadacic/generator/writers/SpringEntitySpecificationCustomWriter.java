package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.querying.Filter;
import com.aleksadacic.engine.user.AppUser;

public class SpringEntitySpecificationCustomWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringEntitySpecificationCustomWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        addImport(Filter.class);
        append(0, "abstract class " + modelObject.getName() + "SpecificationCustom extends " + modelObject.getName() + "SpecificationBase {");
    }

    @Override
    public void writeClassContent() {
        append(1, "protected " + modelObject.getName() + "SpecificationCustom(Filter filter) { super(filter); }");
        append(0, "}");
    }
}
