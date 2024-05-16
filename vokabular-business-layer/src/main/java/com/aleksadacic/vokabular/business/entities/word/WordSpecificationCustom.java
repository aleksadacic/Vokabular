package com.aleksadacic.vokabular.business.entities.word;

import com.aleksadacic.engine.framework.querying.Filter;

abstract class WordSpecificationCustom extends WordSpecificationBase {
	protected WordSpecificationCustom(Filter filter) { super(filter); }
}
