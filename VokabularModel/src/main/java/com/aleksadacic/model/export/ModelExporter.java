package com.aleksadacic.model.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ModelExporter {
    private ModelExporter() {
    }

    public static File export(String sourcePackage, String destinationPath) throws IOException, ClassNotFoundException, AttributeTypeNotSpecifiedException {
        Map<String, List<Class<?>>> all = ModelReader.getClassesInPackage(sourcePackage);

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode entities = collectModelEntities(all.get("classes"), objectMapper);
        ArrayNode enums = collectModelEnums(all.get("enums"), objectMapper);
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.set("entities", entities);
        rootNode.set("enums", enums);

        String json = objectMapper.writeValueAsString(rootNode);
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

    private static ArrayNode collectModelEntities(List<Class<?>> classes, ObjectMapper objectMapper) {
        ArrayNode jsonArray = objectMapper.createArrayNode();
        for (Class<?> clazz : classes) {
            jsonArray.add(ModelClassToJsonExporter.export(clazz));
        }
        return jsonArray;
    }

    private static ArrayNode collectModelEnums(List<Class<?>> classes, ObjectMapper objectMapper) {
        ArrayNode jsonArray = objectMapper.createArrayNode();
        for (Class<?> clazz : classes) {
            jsonArray.add(ModelEnumToJsonExporter.export(clazz));
        }
        return jsonArray;
    }
}
