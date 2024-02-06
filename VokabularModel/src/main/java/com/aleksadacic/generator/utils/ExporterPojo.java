package com.aleksadacic.generator.utils;

import lombok.Data;

import java.util.List;

@Data
public class ExporterPojo {
    String name;
    String outputPath;
    List<String> exporters;
}
