package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.service.EntityController;
import com.aleksadacic.generator.utils.AbstractWriter;
import com.aleksadacic.generator.utils.WriterUtils;

public class SpringEntityControllerBaseWriter extends AbstractWriter {

    public SpringEntityControllerBaseWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport(EntityController.class);
        addImport(WriterUtils.createPackage(WriterUtils.BUSINESS_PACKAGE, modelObject.getName().toLowerCase(), modelObject.getName()));
        append(0, "abstract class " + modelObject.getName() + "ControllerBase extends EntityController<" + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        append(1, "@Override");
        append(1, "public ResponseEntity<?> create(" + modelObject.getName() + "DTO request) {");

        append(1, "}");
        append(0, "}");
    }
}
