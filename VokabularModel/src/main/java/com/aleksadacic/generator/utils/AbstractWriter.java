package com.aleksadacic.generator.utils;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;

//TODO mozda ovo u turbocreator i da se drugacije zove
public abstract class AbstractWriter extends JavaClassWriter {

    public final ModelObject modelObject;

    protected AbstractWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }
}
