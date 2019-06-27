package com.acutest.cdaf;

import java.io.*;
import java.util.*;
import com.esotericsoftware.yamlbeans.YamlReader;

/**
 * Created by Mike Jennings 11/11/2017, based on implementation by Peter Gale.
 *
 * We are using TestFramework as a static object, where all the members are declared static.
 * See: https://www.quora.com/What-is-the-purpose-of-a-static-object-in-Java-When-is-it-actually-used-or-in-which-context
 * This makes the TestFramework object available globally without needing any class instantiation
 *
 */
public class TestConfiguration {
    public static Properties properties = new Properties();

    /**
     * returns the value of a key value pair in System.properties
     * @param propertyName
     * @return
     */
    public static String getProperty(String propertyName) {
        String propertyValue = properties.getProperty(propertyName);
        if (propertyValue == null || propertyValue.equals("")) {
        	propertyValue = System.getenv(propertyName);
        }
        return propertyValue;
    }

    /**
     * Loads data in testConfiguration.yaml file
     *
     * @param sourcePropertiesFilePath
     * @throws Exception
     */
    public static void loadAPropertiesFile(String sourcePropertiesFilePath) throws Exception {
        if (!(sourcePropertiesFilePath == null)) {
            File file = new File(sourcePropertiesFilePath);
            if (!file.exists() || file.isDirectory()) {
                throw new Exception("Cannot find the file: '" + sourcePropertiesFilePath + "'");
            }

            // We may want to ADD to any properties set elsewhere
            if (properties == null) {
                properties = new Properties();
            }
            YamlReader reader = new YamlReader(new FileReader(sourcePropertiesFilePath));
            // TODO: identify the return type of object and replace it with an appropriately parameterised Map
            Object object = reader.read();
            iterateOverAMap((Map) object, null);
        }
    }

    /**
     * Iterates over a map and adds key value pairs to system properties
     * @param map
     * @param baseKey
     */
    private static void iterateOverAMap(Map map, String baseKey) {
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            if (baseKey != null) {
                key = baseKey + "." + key;
            }
            if (entry.getValue() instanceof String) {
                String value = (String) entry.getValue();
                System.setProperty(key, value);
            } else {
                iterateOverAMap((Map) entry.getValue(), key);
            }
        }
    }
}
