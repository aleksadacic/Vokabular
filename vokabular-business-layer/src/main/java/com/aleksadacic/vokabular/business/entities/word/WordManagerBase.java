package com.aleksadacic.vokabular.business.entities.word;

import com.aleksadacic.engine.framework.AttributeDefinition;
import java.util.ArrayList;
import java.util.List;
import com.aleksadacic.vokabular.business.entities.wordtype.WordType;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.vokabular.business.SpringContext;
import com.aleksadacic.engine.framework.business.AbstractBusinessManager;

abstract class WordManagerBase extends AbstractBusinessManager<Word> {
	private static final List<AttributeDefinition> definitions = new ArrayList<>();

	protected WordManagerBase() {
		super(SpringContext.getCurrentUserObject());
	}

	static {
		definitions.add(new AttributeDefinition("id", Id.class, false));
		definitions.add(new AttributeDefinition("value", String.class, false));
		definitions.add(new AttributeDefinition("type", WordType.class, true));
		definitions.add(new AttributeDefinition("usage", String.class, true));
		definitions.add(new AttributeDefinition("meaning", String.class, true));
	}

	@Override
	public Class<Word> getEntityClass() {
		return Word.class;
	}

	@Override
	public List<AttributeDefinition> getAttributeDefinitions() {
		return definitions;
	}
}
