package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.user.AppUser;

public class SpringBusinessManagerWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringBusinessManagerWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        append(0, "public class " + modelObject.getName() + "Manager extends " + modelObject.getName() + "ManagerBase {");
    }

    @Override
    public void writeClassContent() {
        addImport(AppUser.class);
        append(1, "public " + modelObject.getName() + "Manager(AppUser user) { super(user); }");
        append(0, "}");
    }
}
