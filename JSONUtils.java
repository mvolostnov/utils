package com.lseg.testframework.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JSONUtils {

    static final ObjectMapper objectMapper = new ObjectMapper();
    public static JSONContentParser readJson(final String jsonPath) {
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(FileUtils.readAsString(jsonPath));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new JSONContentParser(rootNode);
    }

    public static JSONContentMapper doMapping() {
        return new JSONContentMapper(objectMapper);
    }

    @AllArgsConstructor
    public static class JSONContentParser {

        private JsonNode rootNode;

        public JsonNode getNode(final String fieldName) {
            return rootNode.get(fieldName);
        }
    }

    @AllArgsConstructor
    public static class JSONContentMapper {

        private ObjectMapper objectMapper;

        public <T> T jsonStringToObject(final String jsonContent, final Class<T> classType) {
            Validate.notNullOrBlank(jsonContent, "[JSONUtils] Specified JSON content is NULL or empty/blank!");
            try {
                log.debug("[JSONUtils] Map [" +jsonContent+ "] JSON string to relevant [" +classType+ "] object.");
                return objectMapper.readValue(jsonContent, classType);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            return null;
        }

        public <T> T jsonStringToArray(final String jsonContent, final TypeReference<T> valueTypeRef) {
            Validate.notNullOrBlank(jsonContent, "[JSONUtils] Specified JSON content is NULL or empty/blank!");
            try {
                log.debug("[JSONUtils] Map [" +jsonContent+ "] JSON string to relevant [" +valueTypeRef+ "] array of objects.");
                return objectMapper.readValue(jsonContent, valueTypeRef);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            return null;
        }

        public <T> T jsonFileToObject(final String jsonPath, final Class<T> classType) {
            Validate.notNullOrBlank(jsonPath, "[JSONUtils] Specified JSON path is NULL or empty/blank!");
            try {
                log.debug("[JSONUtils] Map [" +jsonPath+ "] JSON file content to relevant [" +classType+ "] object.");
                return objectMapper.readValue(FileUtils.readAsString(jsonPath), classType);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            return null;
        }

        public String objectToJsonString(final Object object) {
            Validate.notNull(object, "[JSONUtils] Specified object is NULL object!");
            try {
                return objectMapper.writeValueAsString(object);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            return null;
        }

        public File objectToJsonFile(final Object object, final String jsonPath) {
            Validate.notNull(object, "[JSONUtils] Specified object is NULL object!");
            Validate.notNullOrBlank(jsonPath, "[JSONUtils] Specified JSON path is NULL or empty/blank!");
            try {
                final File jsonFile = FileUtils.createFile(jsonPath);
                objectMapper.writeValue(jsonFile, object);
                return jsonFile;
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            return null;
        }
    }
}
