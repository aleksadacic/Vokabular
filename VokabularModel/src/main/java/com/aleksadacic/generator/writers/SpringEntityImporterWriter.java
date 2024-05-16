package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.generator.utils.WriterUtils;

public class SpringEntityImporterWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringEntityImporterWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        append(0, "public class " + modelObject.getName() + "Importer implements EntityImporter<" + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        addImport("com.aleksadacic.engine.dataimport.EntityImporter");
        addImport("com.aleksadacic.engine.dataimport.ImportData");
        addImport("com.aleksadacic.engine.dataimport.ImportRow");
        addImport("com.aleksadacic.engine.exceptions.TurboException");
        addImport("com.aleksadacic.engine.framework.business.BusinessManager");
        addImport(WriterUtils.BUSINESS_PACKAGE + "." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + "");
        addImport(WriterUtils.BUSINESS_PACKAGE + "." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + "Manager");
        addImport("java.io.InputStream");
        append(1, "private final " + modelObject.getName() + "Manager manager;");
        append(1, "private final InputStream file;");
        append(1, "public " + modelObject.getName() + "Importer(" + modelObject.getName() + "Manager manager, InputStream file) {");
        append(2, "this.manager = manager;");
        append(2, "this.file = file;");
        append(1, "}");
        append(1, "@Override");
        append(1, "public void performPersistenceOperation(" + modelObject.getName() + " entity) throws TurboException {");
        append(2, "manager.insert(entity);");
        append(1, "}");
        append(1, "@Override");
        append(1, "public void validatePersistenceOperation(" + modelObject.getName() + " entity) throws TurboException {");
        append(2, "// implement validations");
        append(1, "}");
        append(1, "@Override");
        append(1, "public void transformData(ImportData data) {");
        append(2, "// empty");
        append(1, "}");
        append(1, "@Override");
        append(1, "public " + modelObject.getName() + " createTargetEntity(BusinessManager<" + modelObject.getName() + "> manager, ImportRow row) throws TurboException {");
        append(2, "// implement instantiation of the target entity");
        append(1, "}");
        append(1, "@Override");
        append(1, "public " + modelObject.getName() + "Manager getBusinessManager() {");
        append(2, "return manager;");
        append(1, "}");
        append(1, "@Override");
        append(1, "public Class<" + modelObject.getName() + "> getEntityClass() {");
        append(2, "return " + modelObject.getName() + ".class;");
        append(1, "}");
        append(1, "@Override");
        append(1, "public InputStream getFile() {");
        append(2, "return file;");
        append(1, "}");
        append(0, "}");
    }
}
