package com.aleksadacic.model.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelEnumToJsonExporter {
    private ModelEnumToJsonExporter() {
    }

    static ObjectNode export(Class<?> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Create a JSON object
        ObjectNode jsonObject = objectMapper.createObjectNode();

        EntityUtils entityUtils = new EntityUtils(clazz);
        jsonObject.put("name", entityUtils.extractEntityName());

        ArrayNode attributes = objectMapper.createArrayNode();
        putAttributes(clazz, objectMapper, attributes);
        jsonObject.set("attributes", attributes);

        return jsonObject;
    }

    private static void putAttributes(Class<?> clazz, ObjectMapper objectMapper, ArrayNode attributes) {
        List<Field> fields = new ArrayList<>(List.of(clazz.getSuperclass().getDeclaredFields()));
        fields.addAll(List.of(clazz.getDeclaredFields()).stream().filter(Field::isEnumConstant).collect(Collectors.toList()));
        for (Field field : fields) {
            ObjectNode attribute = objectMapper.createObjectNode();

            EnumAttributeUtils attrUtils = new EnumAttributeUtils(field);
            String name = attrUtils.extractName();
            if (name.equals("name") || name.equals("ordinal")) continue;

            attribute.put("name", name);
            attribute.put("value", attrUtils.extractValue());
            attribute.put("defaultValue", attrUtils.extractDefaultValue());
            attributes.add(attribute);
        }
    }

}
