package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.persistence.AbstractPersistenceManager;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import com.aleksadacic.engine.utils.ConverterUtils;
import com.aleksadacic.generator.utils.AbstractWriter;
import com.aleksadacic.generator.utils.WriterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SpringPersistenceManagerBaseWriter extends AbstractWriter {
    public SpringPersistenceManagerBaseWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport(DataEntityRepository.class);
        addImport(Component.class);
        addImport(Autowired.class);
        addImport("org.modelmapper.ModelMapper");
        addImport(ConverterUtils.class);
        addImport(AbstractPersistenceManager.class);
        addImport(WriterUtils.createPackage(WriterUtils.BUSINESS_PACKAGE, modelObject.getName().toLowerCase(), modelObject.getName()));
        append(0, "@Component");
        append(0, "public class " + modelObject.getName() + "PersistenceManagerBase extends AbstractPersistenceManager<" + modelObject.getName() + ", " + modelObject.getName() + "Entity> {");
    }

    @Override
    public void writeClassContent() {
        append(1, "@Autowired");
        append(1, "private " + modelObject.getName() + "Repository repository;");
        appendBlankLine();
        append(1, "protected " + modelObject.getName() + "PersistenceManagerBase() {");
        append(2, "super(" + modelObject.getName() + ".class, " + modelObject.getName() + "Entity.class);");
        append(1, "}");
        appendBlankLine();
        append(1, "@Override");
        append(1, "protected DataEntityRepository<" + modelObject.getName() + "Entity> getRepository() {");
        append(2, "return repository;");
        append(1, "}");
        append(1, "@Override");
        append(1, "protected " + modelObject.getName() + " convertToBusinessEntity(" + modelObject.getName() + "Entity source) {");
        append(2, "ModelMapper modelMapper = new ModelMapper();");
        append(2, "modelMapper");
        append(4, ".typeMap(" + modelObject.getName() + "Entity.class, " + modelObject.getName() + ".class)");
        append(4, ".addMappings(mapper -> mapper.using(ConverterUtils.STRING_TO_ID).map(" + modelObject.getName() + "Entity::getId, " + modelObject.getName() + "::setId));");
        append(2, "return modelMapper.map(source, " + modelObject.getName() + ".class);");
        append(1, "}");
        append(1, "@Override");
        append(1, "protected " + modelObject.getName() + "Entity convertToPersistenceEntity(" + modelObject.getName() + " source) {");
        append(2, "ModelMapper modelMapper = new ModelMapper();");
        append(2, "modelMapper");
        append(4, ".typeMap(" + modelObject.getName() + ".class, " + modelObject.getName() + "Entity.class)");
        append(4, ".addMappings(mapper -> mapper.map(source1 -> source1.getId().getValue(), " + modelObject.getName() + "Entity::setId));");
        append(2, "return modelMapper.map(source, " + modelObject.getName() + "Entity.class);");
        append(1, "}");
        append(0, "}");
    }
}
