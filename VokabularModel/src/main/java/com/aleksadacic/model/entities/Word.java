package com.aleksadacic.model.entities;

import com.aleksadacic.engine.model.annotations.ModelAttribute;
import com.aleksadacic.engine.model.annotations.ModelEntity;
import com.aleksadacic.model.utils.SystemEntity;

/**
 * TODO doc Genersemo validacije i na servisnom i na biznis sloju da ima i za nas programere i za korisnike servisa.
 */
@ModelEntity(tableName = "vok_word")
public class Word extends SystemEntity {
    @ModelAttribute(title = "Value", minLength = 2, maxLength = 200, tableColumn = "vok_value")
    String value;
    @ModelAttribute(title = "Type", tableColumn = "vok_type")
    String type; //TODO enum
    @ModelAttribute(title = "Usage", tableColumn = "vok_usage")
    String usage;
    @ModelAttribute(title = "Meaning", tableColumn = "vok_meaning")
    String meaning;
}
