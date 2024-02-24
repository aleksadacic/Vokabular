package com.aleksadacic.vokabular.business.entities.word;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = true)
abstract class WordBase extends BusinessEntity {
	protected static final String MODEL_TYPE = "vok_word";
	private Id id;
	private String value;
	private String type;
	private String usage;
	private String meaning;
}
