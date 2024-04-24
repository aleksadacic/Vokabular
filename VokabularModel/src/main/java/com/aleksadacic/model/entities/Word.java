package com.aleksadacic.model.entities;

import com.aleksadacic.engine.model.annotations.ModelAttribute;
import com.aleksadacic.engine.model.annotations.ModelEntity;
import com.aleksadacic.model.utils.SystemEntity;

@SuppressWarnings("unused")
@ModelEntity(tableName = "vok_word")
public class Word extends SystemEntity {
    @ModelAttribute(title = "Value", minLength = 2, maxLength = 200, tableColumn = "vok_value", nullable = false)
    String value;
    @ModelAttribute(title = "Type", tableColumn = "vok_type")
    WordType type;
    @ModelAttribute(title = "Usage", tableColumn = "vok_usage")
    String usage;
    @ModelAttribute(title = "Meaning", tableColumn = "vok_meaning")
    String meaning;
}
