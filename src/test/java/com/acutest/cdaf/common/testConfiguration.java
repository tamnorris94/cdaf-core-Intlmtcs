package com.acutest.cdaf.common;

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
public class testConfiguration {


    public static Properties properties = new Properties();

    public static String getProperty(String propertyName) {
        String propertyValue = properties.getProperty(propertyName);
        return propertyValue;
    }

    public static void loadAPropertiesFile(String sourcePropertiesFilePath) throws Exception {
        if (!(sourcePropertiesFilePath == null)) {
            File file = new File(sourcePropertiesFilePath);
            if (!(file.exists() && !file.isDirectory())) {
                throw new Exception("Cannot find the file: '" + sourcePropertiesFilePath + "'");
            }

            // We may want to ADD to any properties set elsewhere
            if (properties == null) {
                properties = new Properties();
            }
            YamlReader reader = new YamlReader(new FileReader(sourcePropertiesFilePath));
            Object object = reader.read();
            iterateOverAMap((Map) object, null);
        }
    }

    private static void iterateOverAMap(Map map, String baseKey)
    {
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            if (!(baseKey==null)) {
                key = baseKey + "." + key;
            }
            if (entry.getValue() instanceof String) {
                String value = (String) entry.getValue();
                properties.setProperty(key, value);
            } else {
                iterateOverAMap((Map) entry.getValue(), key);
            }
        }
    }
    /*

     */
}
