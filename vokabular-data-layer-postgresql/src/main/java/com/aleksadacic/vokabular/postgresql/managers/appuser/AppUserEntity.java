package com.aleksadacic.vokabular.postgresql.managers.appuser;

import com.aleksadacic.engine.framework.persistence.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "vok_user")
@NoArgsConstructor
@AllArgsConstructor
public class AppUserEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();

    @Override
    public void setId(com.aleksadacic.engine.datatypes.Id id) {
        this.id = id.getValue();
    }

    public void setId(String id) {
        this.id = id;
    }
}