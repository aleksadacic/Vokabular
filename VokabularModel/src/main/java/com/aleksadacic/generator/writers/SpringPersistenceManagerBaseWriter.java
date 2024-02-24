package com.aleksadacic.generator.writers;

import com.aleksadacic.config.TurboCreatorConfig;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import com.aleksadacic.generator.utils.AbstractWriter;
import com.aleksadacic.generator.utils.WriterUtils;
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
        addImport("com.aleksadacic.vokabular.postgresql.AbstractPersistenceManager");
        append(0, "@Component");
        append(0, "public class " + modelObject.getName() + "PersistenceManagerBase extends AbstractPersistenceManager<" + businessEntity + ", " + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        append(1, "@Autowired");
        append(1, "private " + modelObject.getName() + "Repository repository;");
        appendBlankLine();
        append(1, "protected " + modelObject.getName() + "PersistenceManagerBase() {");
        append(2, "super(" + businessEntity + ".class, " + modelObject.getName() + ".class);");
        append(1, "}");
        appendBlankLine();
        append(1, "@Override");
        append(1, "protected DataEntityRepository<" + modelObject.getName() + "> getRepository() {");
        append(2, "return repository;");
        append(1, "}");
        append(1, "@Override");
        append(1, "protected " + WriterUtils.BUSINESS_PACKAGE + "." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + " convertToBusinessEntity(" + modelObject.getName() + " source) {");
        append(2, "ModelMapper modelMapper = new ModelMapper();");
        append(2, "modelMapper");
        append(4, ".typeMap(" + modelObject.getName() + ".class, " + WriterUtils.BUSINESS_PACKAGE + "." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + ".class)");
        append(4, ".addMappings(mapper -> mapper.using(ConverterUtils.STRING_TO_ID).map(" + modelObject.getName() + "::getId, " + WriterUtils.BUSINESS_PACKAGE + "." + modelObject.getName() + "::setId;)");
        append(2, "return modelMapper.map(source, " + WriterUtils.BUSINESS_PACKAGE + "." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + ".class);");
        append(1, "}");
        append(1, "@Override");
        append(1, "protected " + modelObject.getName() + " convertToPersistenceEntity(" + WriterUtils.BUSINESS_PACKAGE + "." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + " source) {");
        append(2, "ModelMapper modelMapper = new ModelMapper();");
        append(2, "modelMapper");
        append(4, ".typeMap(" + WriterUtils.BUSINESS_PACKAGE + "." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + ".class, " + modelObject.getName() + ".class)");
        append(4, ".addMappings(mapper -> mapper.map(source1 -> source1.getId().getValue(), " + modelObject.getName() + "::setId;)");
        append(2, "return modelMapper.map(source, " + modelObject.getName() + ".class);");
        append(1, "}");
        append(0, "}");
    }
}
