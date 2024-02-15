package com.aleksadacic.vokabular.business.users;

import com.aleksadacic.engine.framework.business.BusinessAttribute;

public enum AppUserAttribute implements BusinessAttribute {
    USERNAME("username"),
    PASSWORD("password"),
    ID("id");

    private final String name;

    AppUserAttribute(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
