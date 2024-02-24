package com.aleksadacic.vokabular.postgresql.entities.word;

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
	private String type;
	private String usage;
	private String meaning;

	public void setId (String id){
		this.id = id;
	}
}
