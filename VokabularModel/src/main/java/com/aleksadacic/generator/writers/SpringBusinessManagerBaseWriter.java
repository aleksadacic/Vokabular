package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.business.BusinessEntityManager;
import com.aleksadacic.engine.user.AppUser;

public class SpringBusinessManagerBaseWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringBusinessManagerBaseWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        addImport(BusinessEntityManager.class.getName());
        append(0, "abstract class " + modelObject.getName() + "ManagerBase extends BusinessEntityManager<" + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        addImport(AppUser.class);
        append(1, "protected " + modelObject.getName() + "ManagerBase(AppUser user) { super(user); }");

        append(1, "@Override");
        append(1, "public Class<" + modelObject.getName() + "> getEntityClass() {");
        append(2, "return " + modelObject.getName() + ".class;");
        append(1, "}");

        appendBlankLine();

        append(0, "}");
    }
}
