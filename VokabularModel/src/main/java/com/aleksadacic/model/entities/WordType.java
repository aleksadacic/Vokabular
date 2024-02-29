package com.aleksadacic.model.entities;

import com.aleksadacic.engine.model.annotations.EnumAttribute;
import com.aleksadacic.engine.model.annotations.ModelEnum;

@ModelEnum
public enum WordType {
    @EnumAttribute(value = "imenica")
    IMENICA,
    @EnumAttribute(value = "glagol")
    GLAGOL
}
