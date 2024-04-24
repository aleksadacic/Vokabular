package com.aleksadacic.model.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModelClassToJsonExporter {
    private ModelClassToJsonExporter() {
    }

    static ObjectNode export(Class<?> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode jsonObject = objectMapper.createObjectNode();

        EntityUtils entityUtils = new EntityUtils(clazz);

        jsonObject.put("title", entityUtils.extractEntityTitle());
        jsonObject.put("name", entityUtils.extractEntityName());
        jsonObject.put("tableName", entityUtils.extractTableName());

        ArrayNode attributes = objectMapper.createArrayNode();
        putAttributes(clazz, objectMapper, attributes);
        jsonObject.set("attributes", attributes);

        return jsonObject;
    }

    private static void putAttributes(Class<?> clazz, ObjectMapper objectMapper, ArrayNode attributes) {
        List<Field> fields = new ArrayList<>(List.of(clazz.getSuperclass().getDeclaredFields()));
        fields.addAll(List.of(clazz.getDeclaredFields()));
        for (Field field : fields) {
            ObjectNode attribute = objectMapper.createObjectNode();

            AttributeUtils attrUtils = new AttributeUtils(field);
            attribute.put("name", attrUtils.extractName());
            attribute.put("type", attrUtils.extractType());
            attribute.put("title", attrUtils.extractTitle());
            attribute.put("maxLength", attrUtils.extractMaxLength());
            attribute.put("minLength", attrUtils.extractMinLength());
            attribute.put("nullable", attrUtils.extractNullable());
            attribute.put("unique", attrUtils.extractUnique());
            attribute.put("tableColumn", attrUtils.extractTableColumn());
            attribute.put("enumType", attrUtils.extractEnumType());
            attributes.add(attribute);
        }
    }

}
