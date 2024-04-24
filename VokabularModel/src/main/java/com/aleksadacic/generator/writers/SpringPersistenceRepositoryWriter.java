package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.generator.utils.AbstractWriter;
import org.springframework.stereotype.Repository;

public class SpringPersistenceRepositoryWriter extends AbstractWriter {
    public SpringPersistenceRepositoryWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport(Repository.class);
        append(0, "@Repository");
        append(0, "public interface " + modelObject.getName() + "Repository extends " + modelObject.getName() + "RepositoryBase {");
    }

    @Override
    public void writeClassContent() {
        append(0, "}");
    }
}
