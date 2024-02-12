package com.aleksadacic.vokabular.postgresql.utils;

import org.modelmapper.ModelMapper;

public class PojoConverter {
    private PojoConverter() {
        // prevents instantiation
    }

    public static Object convert(Object source, Class<?> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, targetClass);
    }
}
