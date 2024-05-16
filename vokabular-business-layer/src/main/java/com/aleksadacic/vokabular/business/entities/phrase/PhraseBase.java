package com.aleksadacic.vokabular.business.entities.phrase;

import com.aleksadacic.engine.validations.*;
import com.aleksadacic.engine.datatypes.Id;
import java.util.Objects;
import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.framework.business.BusinessEntity;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = true)
abstract class PhraseBase extends BusinessEntity {
	protected static final String MODEL_TYPE = "vok_phrase";
	private String value;

	@Override
	public void validateSave() throws TurboException {
		if (value == null) {
			throw new NonNullableException();
		}
		if (value.length() < 10) {
			throw new AttributeLengthException();
		}
		if (value.length() > 200) {
			throw new AttributeLengthException();
		}
		super.validateSave();
	}

	@Override
	public void validateUpdate() throws TurboException {
		if (id == null) {
			throw new NonNullableException();
		}
		super.validateInsert();
	}

	@Override
	public void set(String name, Object value) {
		PhraseAttribute attribute = (PhraseAttribute) PhraseAttribute.getByName(name);
		switch (Objects.requireNonNull(attribute)) {
			case ID -> setId(Id.of(value));
			case VALUE -> setValue((String) value);
			default -> super.set(name, value);
		}
	}

	@Override
	public Object get(String name) {
		PhraseAttribute attribute = (PhraseAttribute) PhraseAttribute.getByName(name);
		if (attribute == null) {
			return super.get(name);
		}
		return switch (attribute) {
			case ID -> getId();
			case VALUE -> getValue();
		};
	}
}
