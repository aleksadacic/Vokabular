package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.utils.JavaUtils;
import com.aleksadacic.creator.turbo.exceptions.TypeNotFoundException;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.reader.ModelObjectAttribute;
import com.aleksadacic.creator.turbo.utils.TypeDefinition;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import com.aleksadacic.engine.utils.StringUtils;
import com.aleksadacic.generator.utils.AbstractWriter;
import com.aleksadacic.generator.utils.WriterUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

public class SpringPersistenceRepositoryBaseWriter extends AbstractWriter {
    public SpringPersistenceRepositoryBaseWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport(JpaRepository.class);
        addImport(JpaSpecificationExecutor.class);
        addImport(DataEntityRepository.class);
        addImport(Optional.class);
        addImport(NoRepositoryBean.class);
        append(0, "@NoRepositoryBean");
        append(0, "@SuppressWarnings(\"unused\")");
        append(0, "public interface " + modelObject.getName() + "RepositoryBase extends JpaRepository<" + modelObject.getName() + ", String>,  JpaSpecificationExecutor <" + modelObject.getName() + ">, DataEntityRepository<" + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            try {
                append(1, "Optional<" + modelObject.getName() + "> findBy" + StringUtils.capitalize(attribute.getName()) + "(" + JavaUtils.getJavaType(TypeDefinition.valueOf(attribute.getType().toUpperCase())) + " " + attribute.getName() + ");");
            } catch (TypeNotFoundException | IllegalArgumentException e) {
                if (!attribute.getType().equals("primaryKey")) {
                    addImport(WriterUtils.DATA_PACKAGE + "." + attribute.getType().toLowerCase() + "." + attribute.getType());
                    append(1, "Optional<" + modelObject.getName() + "> findBy" + StringUtils.capitalize(attribute.getName()) + "(" + attribute.getType() + " " + attribute.getName() + ");");
                }
            }
        }
        appendBlankLine();
        append(0, "}");
    }
}
