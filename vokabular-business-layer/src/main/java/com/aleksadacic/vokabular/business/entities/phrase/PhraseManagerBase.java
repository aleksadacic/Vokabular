package com.aleksadacic.vokabular.business.entities.phrase;

import com.aleksadacic.engine.framework.AttributeDefinition;
import java.util.ArrayList;
import java.util.List;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.vokabular.business.SpringContext;
import com.aleksadacic.engine.framework.business.AbstractBusinessManager;

abstract class PhraseManagerBase extends AbstractBusinessManager<Phrase> {
	private static final List<AttributeDefinition> definitions = new ArrayList<>();

	protected PhraseManagerBase() {
		super(SpringContext.getCurrentUserObject());
	}

	static {
		definitions.add(new AttributeDefinition("id", Id.class, false));
		definitions.add(new AttributeDefinition("value", String.class, false));
	}

	@Override
	public Class<Phrase> getEntityClass() {
		return Phrase.class;
	}

	@Override
	public List<AttributeDefinition> getAttributeDefinitions() {
		return definitions;
	}
}
