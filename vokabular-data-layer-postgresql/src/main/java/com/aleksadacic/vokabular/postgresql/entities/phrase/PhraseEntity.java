package com.aleksadacic.vokabular.postgresql.entities.phrase;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import com.aleksadacic.engine.framework.persistence.PersistenceEntity;

@Entity
@Data
@Table(name = "vok_phrase")
@NoArgsConstructor
@AllArgsConstructor
public class PhraseEntity implements PersistenceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String value;
}
