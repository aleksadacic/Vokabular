package com.aleksadacic.model.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ModelExporter {
    private ModelExporter() {
    }

    public static File export(String sourcePackage, String destinationPath) throws IOException, ClassNotFoundException, AttributeTypeNotSpecifiedException {
        List<Class<?>> classes = ModelReader.getClassesInPackage(sourcePackage);

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode entities = collectModelEntities(classes, objectMapper);
        String json = objectMapper.writeValueAsString(entities);
        return writeFile(json, destinationPath);
    }

    private static File writeFile(String json, String destinationPath) throws IOException {
        File file = new File(destinationPath);
        //noinspection ResultOfMethodCallIgnored
        file.getParentFile().mkdirs();
        //noinspection ResultOfMethodCallIgnored
        file.createNewFile();
        writeToFile(json, file);
        return file;
    }

    private static void writeToFile(String json, File file) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(json);
        }
    }

    private static ArrayNode collectModelEntities(List<Class<?>> classes, ObjectMapper objectMapper) throws AttributeTypeNotSpecifiedException {
        ArrayNode jsonArray = objectMapper.createArrayNode();
        for (Class<?> clazz : classes) {
            jsonArray.add(ModelClassToJsonExporter.export(clazz));
        }
        return jsonArray;
    }
}
