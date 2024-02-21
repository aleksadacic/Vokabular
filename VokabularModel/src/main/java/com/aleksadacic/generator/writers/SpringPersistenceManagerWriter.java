package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import com.aleksadacic.generator.utils.AbstractWriter;
import org.springframework.stereotype.Component;

public class SpringPersistenceManagerWriter extends AbstractWriter {
    public SpringPersistenceManagerWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport(DataEntityRepository.class);
        addImport(Component.class);
        append(0, "@Component");
        append(0, "public class " + modelObject.getName() + "PersistenceManager extends " + modelObject.getName() + "PersistenceManagerBase {");
    }

    @Override
    public void writeClassContent() {
        append(0, "}");
    }
}
