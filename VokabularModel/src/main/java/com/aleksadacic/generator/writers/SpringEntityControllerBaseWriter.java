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
        append(0, "abstract class " + modelObject.getName() + "ControllerBase implements EntityController<" + modelObject.getName() + "DTO, " + modelObject.getName() + "SearchDTO" + "> {");
    }

    @Override
    public void writeClassContent() {
        addImport("com.aleksadacic.engine.datatypes.Id");
        addImport("com.aleksadacic.engine.exceptions.BadParametersException");
        addImport("com.aleksadacic.engine.exceptions.DataNotFoundException");
        addImport("com.aleksadacic.engine.exceptions.ServiceException");
        addImport("com.aleksadacic.engine.framework.service.ServiceUtils");
        addImport("com.aleksadacic.vokabular.business.entities." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + "Manager");
        addImport("com.aleksadacic.vokabular.business.entities." + modelObject.getName().toLowerCase() + "." + modelObject.getName() + "Specification");
        addImport("jakarta.validation.constraints.NotNull");
        addImport("org.springframework.beans.factory.annotation.Autowired");
        addImport("org.springframework.data.domain.Sort");
        addImport("org.springframework.http.ResponseEntity");
        addImport("java.util.Optional");
        append(1, "@Autowired");
        append(1, "protected " + modelObject.getName() + "Manager manager;");
        append(1, "@Override");
        append(1, "public ResponseEntity<?> create() {");
        append(2, "try {");
        append(3, "return ResponseEntity.ok(manager.create());");
        append(2, "} catch (Exception e) {");
        append(3, "e.printStackTrace();");
        append(3, "return ServiceUtils.errorResponse(e);");
        append(2, "}");
        append(1, "}");
        append(1, "@Override");
        append(1, "public ResponseEntity<?> insert(" + modelObject.getName() + "DTO request) {");
        append(2, "try {");
        append(3, "" + modelObject.getName() + " entity = request.convertToBusinessEntity();");
        append(3, "if (entity == null) {");
        append(4, "throw new BadParametersException();");
        append(3, "}");
        append(3, "return ResponseEntity.ok(manager.insert(entity));");
        append(2, "} catch (Exception e) {");
        append(3, "e.printStackTrace();");
        append(3, "return ServiceUtils.errorResponse(e);");
        append(2, "}");
        append(1, "}");
        append(1, "@Override");
        append(1, "public ResponseEntity<?> update(" + modelObject.getName() + "DTO request) {");
        append(2, "try {");
        append(3, "" + modelObject.getName() + " entity = request.convertToBusinessEntity();");
        append(3, "if (entity == null) {");
        append(4, "throw new ServiceException();");
        append(3, "}");
        append(3, "return ResponseEntity.ok(manager.update(entity));");
        append(2, "} catch (Exception e) {");
        append(3, "e.printStackTrace();");
        append(3, "return ServiceUtils.errorResponse(e);");
        append(2, "}");
        append(1, "}");
        append(1, "@Override");
        append(1, "public ResponseEntity<?> delete(@NotNull String id) {");
        append(2, "try {");
        append(3, "Optional<" + modelObject.getName() + "> entity = manager.getById(Id.of(id));");
        append(3, "if (entity.isEmpty()) {");
        append(4, "throw new DataNotFoundException();");
        append(3, "}");
        append(3, "manager.delete(entity.get());");
        append(3, "return ResponseEntity.ok().build();");
        append(2, "} catch (Exception e) {");
        append(3, "e.printStackTrace();");
        append(3, "return ServiceUtils.errorResponse(e);");
        append(2, "}");
        append(1, "}");
        append(1, "@Override");
        append(1, "public ResponseEntity<?> search(" + modelObject.getName() + "SearchDTO request) {");
        append(2, "try {");
        append(3, "if (request.getPageNumber() == null || request.getPageSize() == null) {");
        append(4, "throw new BadParametersException(\"pageNumber\", \"pageSize\");");
        append(3, "}");
        append(3, "" + modelObject.getName() + "Specification specification = request.buildSpecification();");
        append(3, "if (request.getSort() != null && !request.getSort().isEmpty()) {");
        append(4, "manager.setSort(Sort.by((String[]) request.getSort().toArray()));");
        append(3, "}");
        append(3, "return ResponseEntity.ok(manager.getPageData(specification));");
        append(2, "} catch (Exception e) {");
        append(3, "e.printStackTrace();");
        append(3, "return ServiceUtils.errorResponse(e);");
        append(2, "}");
        append(1, "}");
        append(1, "@Override");
        append(1, "public ResponseEntity<?> getById(@NotNull String id) {");
        append(2, "try {");
        append(3, "return ResponseEntity.ok(manager.getById(Id.of(id)));");
        append(2, "} catch (Exception e) {");
        append(3, "e.printStackTrace();");
        append(3, "return ServiceUtils.errorResponse(e);");
        append(2, "}");
        append(1, "}");
        append(1, "@Override");
        append(1, "public ResponseEntity<?> count(" + modelObject.getName() + "SearchDTO request) {");
        append(2, "try {");
        append(3, "" + modelObject.getName() + "Specification specification = request.buildSpecification();");
        append(3, "return ResponseEntity.ok(manager.count(specification));");
        append(2, "} catch (Exception e) {");
        append(3, "e.printStackTrace();");
        append(3, "return ServiceUtils.errorResponse(e);");
        append(2, "}");
        append(1, "}");
        append(1, "@Override");
        append(1, "public ResponseEntity<?> getAll() {");
        append(2, "try {");
        append(3, "return ResponseEntity.ok(manager.getData());");
        append(2, "} catch (Exception e) {");
        append(3, "e.printStackTrace();");
        append(3, "return ServiceUtils.errorResponse(e);");
        append(2, "}");
        append(1, "}");
        append(1, "@Override");
        append(1, "public ResponseEntity<?> getData(" + modelObject.getName() + "SearchDTO request) {");
        append(2, "try {");
        append(3, "" + modelObject.getName() + "Specification specification = request.buildSpecification();");
        append(3, "if (request.getPageNumber() != null && request.getPageSize() != null) {");
        append(4, "manager.setPageable(request.getPageNumber(), request.getPageSize());");
        append(3, "}");
        append(3, "if (request.getSort() != null && !request.getSort().isEmpty()) {");
        append(4, "manager.setSort(Sort.by((String[]) request.getSort().toArray()));");
        append(3, "}");
        append(3, "return ResponseEntity.ok(manager.getData(specification));");
        append(2, "} catch (Exception e) {");
        append(3, "e.printStackTrace();");
        append(3, "return ServiceUtils.errorResponse(e);");
        append(2, "}");
        append(1, "}");
        append(0, "}");
    }
}
