package me.fileUpload.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AlbertRui
 * @create 2018-02-24 13:12
 */
public class FileUploadProperties {
    private FileUploadProperties(){}
    private Map<String, String> properties = new HashMap<>();
    private static FileUploadProperties instance = new FileUploadProperties();

    public static FileUploadProperties getInstance() {
        return instance;
    }

    public void addProperty(String propertyName, String propertyValue) {
        properties.put(propertyName, propertyValue);
    }

    public String getProperty(String propertyName) {
        return properties.get(propertyName);
    }
}
