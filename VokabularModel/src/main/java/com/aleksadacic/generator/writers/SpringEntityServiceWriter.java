package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.utils.StringUtils;
import com.aleksadacic.generator.utils.AbstractWriter;

public class SpringEntityServiceWriter extends AbstractWriter {
    public SpringEntityServiceWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport("org.springframework.web.bind.annotation.*;");

        append(0, "@RestController");
        append(0, "@RequestMapping(\"/api/" + StringUtils.decapitalize(modelObject.getName()) + "\")");
        append(0, "public class " + modelObject.getName() + "Service extends " + modelObject.getName() + "Controller {");
    }

    @Override
    public void writeClassContent() {
        addImport("org.springframework.lang.NonNull");
        addImport("org.springframework.http.ResponseEntity");
        addImport("org.springframework.http.MediaType");
        addImport("org.springframework.web.bind.annotation.*");
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/create\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> create() {");
        append(2, "return super.create();");
        append(1, "}");
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/insert\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> insert(@RequestBody " + modelObject.getName() + "DTO request) {");
        append(2, "return super.insert(request);");
        append(1, "}");
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/update\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> update(@RequestBody " + modelObject.getName() + "DTO request) {");
        append(2, "return super.update(request);");
        append(1, "}");
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/delete\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> delete(@RequestBody String id) {");
        append(2, "return super.delete(id);");
        append(1, "}");
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/search\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> search(@RequestBody " + modelObject.getName() + "SearchDTO request) {");
        append(2, "return super.search(request);");
        append(1, "}");
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/getById\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> getById(@RequestBody @NonNull String id) {");
        append(2, "return super.getById(id);");
        append(1, "}");
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/count\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> count(" + modelObject.getName() + "SearchDTO request) {");
        append(2, "return super.count(request);");
        append(1, "}");
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/getAll\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> getAll() {");
        append(2, "return super.getAll();");
        append(1, "}");
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/getData\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> getData(" + modelObject.getName() + "SearchDTO request) {");
        append(2, "return super.getData(request);");
        append(1, "}");
        append(0, "}");

    }
}
