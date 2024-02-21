package com.aleksadacic.generator.writers;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.reader.ModelObjectAttribute;
import com.aleksadacic.engine.framework.persistence.PersistenceEntity;
import com.aleksadacic.generator.utils.AbstractWriter;

//@Data
//@Entity
//@Table(name = "vok_user")
//@NoArgsConstructor
//@AllArgsConstructor
//public class AppUser implements PersistenceEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String id;
//
//    private String username;
//    private String password;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<UserRole> roles = new HashSet<>();
//
//    @Override
//    public void setId(com.aleksadacic.engine.datatypes.Id id) {
//        this.id = id.getValue();
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//}

public class SpringPersistenceEntityWriter extends AbstractWriter {
    public SpringPersistenceEntityWriter(ModelObject modelObject, String classPackage) {
        super(modelObject, classPackage);
    }

    @Override
    public void writeClassHeader() {
        addImport(PersistenceEntity.class);
        addImport("jakarta.persistence.*");
        addImport("lombok.Data");
        addImport("lombok.NoArgsConstructor");
        addImport("lombok.AllArgsConstructor");

        append(0, "@Entity");
        append(0, "@Data");
        append(0, "@Table(name = \"" + modelObject.getTableName() + "\")");
        append(0, "@NoArgsConstructor");
        append(0, "@AllArgsConstructor");
        append(0, "public interface " + modelObject.getName() + "Repository extends " + modelObject.getName() + " RepositoryBase {");
    }

    @Override
    public void writeClassContent() {
        append(1, "@Id");
        append(1, "@GeneratedValue(strategy = GenerationType.UUID)");
        append(1, "private String id;");

        for (ModelObjectAttribute attribute : modelObject.getAttributes()) {
            append(1, "private " + attribute.getType() + " " + attribute.getName());
        }

        append(0, "}");
    }
}
