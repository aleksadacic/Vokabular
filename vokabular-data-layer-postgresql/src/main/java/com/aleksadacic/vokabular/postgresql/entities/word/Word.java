package com.aleksadacic.vokabular.postgresql.entities.word;

import com.aleksadacic.engine.framework.persistence.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "vok_word")
@NoArgsConstructor
@AllArgsConstructor
public class Word implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String value;
    private String type;
    private String usage;
    private String meaning;

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
