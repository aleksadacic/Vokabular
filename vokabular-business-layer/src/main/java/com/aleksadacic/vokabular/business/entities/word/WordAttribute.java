package com.aleksadacic.vokabular.business.entities.word;

import com.aleksadacic.engine.framework.business.BusinessAttribute;

public enum WordAttribute implements BusinessAttribute {
	ID("id"),
	VALUE("value"),
	TYPE("type"),
	USAGE("usage"),
	MEANING("meaning"),
	;

	private final String name;
	WordAttribute(String name) {
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
