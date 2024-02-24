package com.aleksadacic.vokabular.postgresql.entities.appuser;

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
    public void setId(String id) {
        this.id = id;
    }
}
