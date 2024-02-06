package com.aleksadacic.model.utils;

import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.model.annotations.ModelAttribute;

public class SystemEntity {
    @ModelAttribute(title = "id", nullable = false, unique = true, tableColumn = "id")
    Id id;
}
