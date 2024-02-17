package com.aleksadacic.model.export;

import com.aleksadacic.engine.exceptions.TurboException;

public class AttributeTypeNotSpecifiedException extends TurboException {
    private final String type;

    public AttributeTypeNotSpecifiedException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "Type not found: [" + type + "]";
    }
}
