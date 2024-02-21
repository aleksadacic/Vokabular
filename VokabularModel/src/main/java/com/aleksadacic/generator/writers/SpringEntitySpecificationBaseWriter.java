package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.languages.java.writers.JavaClassWriter;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.engine.framework.business.BusinessAttribute;
import com.aleksadacic.engine.framework.business.SpecificationContainer;
import com.aleksadacic.engine.framework.querying.Filter;
import com.aleksadacic.engine.framework.querying.SearchOperator;
import com.aleksadacic.engine.user.AppUser;
import org.springframework.data.jpa.domain.Specification;

public class SpringEntitySpecificationBaseWriter extends JavaClassWriter {
    private final ModelObject modelObject;

    public SpringEntitySpecificationBaseWriter(ModelObject modelObject, String classPackage) {
        super(classPackage);
        this.modelObject = modelObject;
    }

    @Override
    public void writeClassHeader() {
        addImport(BusinessAttribute.class);
        addImport(SpecificationContainer.class);
        addImport(Filter.class);
        addImport(SearchOperator.class);
        addImport("com.aleksadacic.vokabular.business.BusinessSpecification");
        addImport(Specification.class);
        append(0, "abstract class " + modelObject.getName() + "SpecificationBase implements SpecificationContainer< " + modelObject.getName() + "> {");
    }

    @Override
    public void writeClassContent() {
        addImport(AppUser.class);
        append(1, "protected " + modelObject.getName() + "SpecificationBase(Filter filter) {");
        append(2, "this.specification = Specification.where(new BusinessSpecification<>(filter));");
        append(1, "}");
        appendBlankLine();
        append(1, "public static " + modelObject.getName() + " Specification where(BusinessAttribute key, SearchOperator operation, Object value) {");
        append(2, "return new AppUserSpecification(new Filter(key, operation, value));");
        append(1, "}");
        appendBlankLine();
        append(1, "public static " + modelObject.getName() + " Specification where(BusinessAttribute key, SearchOperator operation) {");
        append(2, "return new AppUserSpecification(new Filter(key, operation));");
        append(1, "}");
        appendBlankLine();
        append(1, "public static " + modelObject.getName() + "Specification and(BusinessAttribute key, Object value) {");
        append(2, "specification = specification.and(new BusinessSpecification<>(new Filter(key, value)));");
        append(2, "return (" + modelObject.getName() + "Specification) this;");
        append(1, "}");
        appendBlankLine();
        append(1, "public static " + modelObject.getName() + "Specification and(BusinessAttribute key, SearchOperator operation, Object value) {");
        append(2, "specification = specification.and(new BusinessSpecification<>(new Filter(key, operation, value)));");
        append(2, "return (" + modelObject.getName() + "Specification) this;");
        append(1, "}");
        appendBlankLine();
        append(1, "public static " + modelObject.getName() + "Specification and(" + modelObject.getName() + "Specification userSpec) {");
        append(2, "specification = specification.and(userSpec.getSpecification());");
        append(2, "return (" + modelObject.getName() + "Specification) this;");
        append(1, "}");
        appendBlankLine();
        append(1, "public static " + modelObject.getName() + "Specification or(BusinessAttribute key, Object value) {");
        append(2, "specification = specification.or(new BusinessSpecification<>(new Filter(key, value)));");
        append(2, "return (" + modelObject.getName() + "Specification) this;");
        append(1, "}");
        appendBlankLine();
        append(1, "public static " + modelObject.getName() + "Specification or(BusinessAttribute key, SearchOperator operation, Object value) {");
        append(2, "specification = specification.or(new BusinessSpecification<>(new Filter(key, operation, value)));");
        append(2, "return (" + modelObject.getName() + "Specification) this;");
        append(1, "}");
        appendBlankLine();
        append(1, "public static " + modelObject.getName() + "Specification or(" + modelObject.getName() + "Specification userSpec) {");
        append(2, "specification = specification.or(userSpec.getSpecification());");
        append(2, "return (" + modelObject.getName() + "Specification) this;");
        append(1, "}");
        appendBlankLine();
        append(1, "@Override");
        append(1, "public Specification<" + modelObject.getName() + "> getSpecification() { return specification; }");

        append(0, "}");
    }
}
