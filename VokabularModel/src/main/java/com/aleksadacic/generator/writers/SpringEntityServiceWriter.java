package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.utils.StringUtils;
import com.aleksadacic.generator.utils.AbstractWriter;
import org.springframework.lang.NonNull;

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
        addImport("org.springframework.http.MediaType");
        addImport("org.springframework.http.ResponseEntity");
        addImport("javax.validation.Valid");
        addImport(NonNull.class);

        append(1, "@Override");
        append(1, "@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> create(@RequestBody @Valid " + modelObject.getName() + "DTO request) { return super.create(request); }");
        appendBlankLine();
        append(1, "@Override");
        append(1, "@PostMapping(value = \"update\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> update(@RequestBody @Valid " + modelObject.getName() + "DTO request) { return super.update(request); }");
        appendBlankLine();
        append(1, "@Override");
        append(1, "@PostMapping(value = \"delete\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> delete(@PathVariable String id) { return super.delete(id); }");
        appendBlankLine();
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/search\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> search(@RequestBody @Valid " + modelObject.getName() + "DTO request) { return super.search(request); }");
        appendBlankLine();
        append(1, "@Override");
        append(1, "@PostMapping(value = \"/getById\", produces = MediaType.APPLICATION_JSON_VALUE)");
        append(1, "public ResponseEntity<?> getById(@RequestBody @NonNull String id) { return super.getById(id); }");

        append(0, "}");
    }
}
