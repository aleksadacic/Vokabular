package com.aleksadacic.model.export;

public class AttributeTypeNotSpecifiedException extends Exception {
    //TODO neki apstraktni excpetion s tipovima kao ecmexc
    private final String type;

    public AttributeTypeNotSpecifiedException(String type) {
        this.type = type;

    }

    @Override
    public String getMessage() {
        return "Type not found: [" + type + "]";
    }
}
