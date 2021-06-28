package com.test.test.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JacksonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private JacksonUtils() {
    }

    public static String getPlainJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException var4) {
            logger.error("JackUtilsException", var4);
        }

        return json;
    }
}
