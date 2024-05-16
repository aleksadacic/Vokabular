package com.aleksadacic.vokabular.business.entities.phrase;

import com.aleksadacic.engine.framework.business.BusinessAttribute;

public enum PhraseAttribute implements BusinessAttribute {
	ID("id"),
	VALUE("value"),
	;

	private final String name;
	PhraseAttribute(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public static BusinessAttribute getByName(String name) {
		for (BusinessAttribute attribute : values()) {
			if (attribute.getName().equals(name))
				return attribute;
			}
		return null;
	}
}
