package com.aleksadacic.vokabular.postgresql.entities.word;

import com.aleksadacic.vokabular.postgresql.entities.wordtype.WordType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import com.aleksadacic.engine.framework.persistence.PersistenceEntity;

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
	@Enumerated(EnumType.STRING)
	private WordType type;
	private String usage;
	private String meaning;
}
