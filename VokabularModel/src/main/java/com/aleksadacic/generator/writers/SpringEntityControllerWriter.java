package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.generator.utils.AbstractWriter;

public class SpringEntityControllerWriter extends AbstractWriter {
    public SpringEntityControllerWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        append(0, "abstract class " + modelObject.getName() + "Controller extends " + modelObject.getName() + "ControllerBase {");
    }

    @Override
    public void writeClassContent() {
        append(0, "}");
    }
}
