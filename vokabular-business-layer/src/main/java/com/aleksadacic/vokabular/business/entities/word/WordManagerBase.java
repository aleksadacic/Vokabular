package com.aleksadacic.vokabular.business.entities.word;

import com.aleksadacic.vokabular.business.AbstractBusinessManager;
import com.aleksadacic.engine.framework.business.BusinessManager;

abstract class WordManagerBase extends AbstractBusinessManager<Word> implements BusinessManager<Word> {
	protected WordManagerBase() {}

	@Override
	public Class<Word> getEntityClass() {
		return Word.class;
	}
}
