package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.model.annotations.EntityPersistenceManager;
import com.aleksadacic.generator.utils.AbstractWriter;
import com.aleksadacic.generator.utils.WriterUtils;
import org.springframework.stereotype.Component;

//TODO da se namesti da classheader zapravo svuda bude overwritable
public class SpringPersistenceManagerWriter extends AbstractWriter {
    public SpringPersistenceManagerWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport(Component.class);
        addImport(EntityPersistenceManager.class);
        addImport(WriterUtils.createPackage(WriterUtils.BUSINESS_PACKAGE, modelObject.getName().toLowerCase(), modelObject.getName()));
        append(0, "@Component");
        append(0, "@EntityPersistenceManager(businessClass=" + modelObject.getName() + ".class)");
        append(0, "public class " + modelObject.getName() + "PersistenceManager extends " + modelObject.getName() + "PersistenceManagerBase {");
    }

    @Override
    public void writeClassContent() {
        append(0, "}");
    }
}
