package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.reader.ModelObjectAttribute;
import com.aleksadacic.engine.framework.persistence.DataEntityRepository;
import com.aleksadacic.engine.utils.StringUtils;
import com.aleksadacic.generator.utils.AbstractWriter;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public class SpringPersistenceRepositoryBaseWriter extends AbstractWriter {
    public SpringPersistenceRepositoryBaseWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport(Repository.class);
        addImport(DataEntityRepository.class);
        addImport(Optional.class);
        addImport(NoRepositoryBean.class);
        append(0, "@NoRepositoryBean");
        append(0, "public interface " + modelObject.getName() + "RepositoryBase extends JpaRepository<" + modelObject.getName() + ", String>,  JpaSpecificationExecutor <" + modelObject.getName() + ">, DataEntityRepository<" + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            append(1, "Optional<" + modelObject.getName() + "> findBy" + StringUtils.capitalize(attribute.getName()) + "(" + attribute.getType() + " " + attribute.getName() + ");");
        }
        append(0, "}");
    }
}
