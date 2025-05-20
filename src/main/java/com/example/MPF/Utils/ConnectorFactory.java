package com.example.MPF.Utils;

import com.example.MPF.Model.Config;

import java.lang.reflect.Constructor;

public class ConnectorFactory {

    private static String BASE_PACKAGE = "com.example.MPF";

    public static Object createIngress(String type, Config config){
        String fullyQualifiedName = BASE_PACKAGE + ".Ingresess." + type;
        try {
            // Load the class using its fully qualified name
            Class<?> ingressClass = Class.forName(fullyQualifiedName);
            // Retrieve the constructor that takes a Config parameter
            Constructor<?> constructor = ingressClass.getConstructor(Config.class);
            // Instantiate and return the object
            return constructor.newInstance(config);
        }catch (Exception e){
            throw new IllegalArgumentException("Cannot instantiate ingress type: " + type, e);
        }
    }

    public static Object createEgress(String type, Config config){
        String fullyQualifiedName = BASE_PACKAGE + ".Egresess." + type;
        try {
            // Load the class using its fully qualified name
            Class<?> egressClass = Class.forName(fullyQualifiedName);
            // Retrieve the constructor that takes a Config parameter
            Constructor<?> constructor = egressClass.getConstructor(Config.class);
            // Instantiate and return the object
            return constructor.newInstance(config);
        }catch (Exception e){
            throw new IllegalArgumentException("Cannot instantiate ingress type: " + type, e);
        }
    }

}
