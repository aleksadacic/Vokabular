package com.aleksadacic.vokabular.business.enums.wordtype;

public enum WordType {
	IMENICA("imenica"),
	GLAGOL("glagol"),
	;

	private final String value;
	WordType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
