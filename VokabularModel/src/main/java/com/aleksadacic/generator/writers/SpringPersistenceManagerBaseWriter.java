package com.aleksadacic.generator.writers;

import com.aleksadacic.config.TurboCreatorConfig;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import com.aleksadacic.generator.utils.AbstractWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SpringPersistenceManagerBaseWriter extends AbstractWriter {
    public SpringPersistenceManagerBaseWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    private final String businessEntity = TurboCreatorConfig.getBusinessPackage() + "." + modelObject.getName().toLowerCase() + "." + modelObject.getName();

    @Override
    public void writeClassHeader() {
        addImport(DataEntityRepository.class);
        addImport(Component.class);
        addImport(Autowired.class);
        append(0, "@Component");
        append(0, "public class " + modelObject.getName() + "PersistenceManagerBase extends AbstractPersistenceManager<" + businessEntity + ", " + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        append(1, "@Autowired");
        append(1, "private " + modelObject.getName() + "Repository repository;");
        appendBlankLine();
        append(1, "protected " + modelObject.getName() + "PersistenceManagerBase() {");
        append(2, "super(" + businessEntity + ", " + modelObject.getName() + ".class);");
        append(1, "}");
        appendBlankLine();
        append(1, "@Override");
        append(1, "protected DataEntityRepository<" + modelObject.getName() + "> getRepository() {");
        append(2, "return repository;");
        append(1, "}");
        append(0, "}");
    }
}
