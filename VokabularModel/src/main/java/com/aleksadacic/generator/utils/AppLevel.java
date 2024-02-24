package com.aleksadacic.generator.utils;

public enum AppLevel {
    BUSINESS("BUSINESS"),
    DATA("DATA"),
    SERVICE("SERVICE");
    private final String name;

    AppLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
