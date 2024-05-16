package com.aleksadacic.model.entities;


import com.aleksadacic.engine.model.annotations.ModelAttribute;
import com.aleksadacic.engine.model.annotations.ModelEntity;
import com.aleksadacic.model.utils.SystemEntity;

@SuppressWarnings("unused")
@ModelEntity(tableName = "vok_phrase")
public class Phrase extends SystemEntity {
    @ModelAttribute(title = "Value", minLength = 10, maxLength = 200, tableColumn = "vok_value", nullable = false)
    String value;
}
