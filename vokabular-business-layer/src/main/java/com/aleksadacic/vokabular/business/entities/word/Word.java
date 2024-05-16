package com.aleksadacic.vokabular.business.entities.word;

import com.aleksadacic.engine.exceptions.DataNotFoundException;
import com.aleksadacic.engine.exceptions.TurboException;

public class Word extends WordBase {
    @Override
    public void validateInsert() throws TurboException {
        try {
            new WordManager().getUnique(WordSpecification.where(WordAttribute.VALUE, getValue()));
            throw new TurboException("Entity already exists!");
        } catch (DataNotFoundException e) {
            // skip, we didn't found the element
        }
        super.validateInsert();
    }
}
