package com.clubbeer.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HeaderUtil {
    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);
    private static final String APPLICATION_NAME = "Club-Beer";

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String applicationName, String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + applicationName + "-alert", message);

        try {
            headers.add("X-" + applicationName + "-params", URLEncoder.encode(param, StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException var5) {
        }

        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(boolean enableTranslation, String entityName, String param) {
        String message = enableTranslation ? APPLICATION_NAME + "." + entityName + ".created" : "A new " + entityName + " is created with identifier " + param;
        return createAlert(APPLICATION_NAME, message, param);
    }

    public static HttpHeaders createEntityUpdateAlert(boolean enableTranslation, String entityName, String param) {
        String message = enableTranslation ? APPLICATION_NAME + "." + entityName + ".updated" : "A " + entityName + " is updated with identifier " + param;
        return createAlert(APPLICATION_NAME, message, param);
    }

    public static HttpHeaders createEntityDeletionAlert(boolean enableTranslation, String entityName, String param) {
        String message = enableTranslation ? APPLICATION_NAME + "." + entityName + ".deleted" : "A " + entityName + " is deleted with identifier " + param;
        return createAlert(APPLICATION_NAME, message, param);
    }

    public static HttpHeaders createFailureAlert(boolean enableTranslation, String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        String message = enableTranslation ? "error." + errorKey : defaultMessage;
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + APPLICATION_NAME + "-error", message);
        headers.add("X-" + APPLICATION_NAME + "-params", entityName);
        return headers;
    }
}
