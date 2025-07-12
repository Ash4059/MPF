package com.example.MPF.Utils;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ClassRegistry {

    private Map<String, Class<?>> classMap;
    private static final Logger logger = LoggerFactory.getLogger(ClassRegistry.class);
    String packageName = "com.example.MPF";

    public ClassRegistry(){
        this.classMap = new HashMap<>();
    }

    @PostConstruct
    public void initializeClassMap(){
        // Use Reflections to find all classes in the package
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        // Add the classes to the map
        // include everything by default
        scanner.addIncludeFilter(new AssignableTypeFilter(Object.class));

        Set<BeanDefinition> candidates = scanner.findCandidateComponents(this.packageName);
        for(BeanDefinition bd : candidates){
            String className = bd.getBeanClassName();
            try {
                Class<?> clazz = Class.forName(className);
                classMap.put(clazz.getSimpleName(), clazz);
            } catch (ClassNotFoundException e) {
                logger.error("No class name found :" + e.getMessage());
                throw new RuntimeException(e);
            }
        }

    }

    public Class<?> getClassByName(String className){
        return classMap.get(className);
    }

    public Object getInstance(String className) {
        Class<?> clazz = null;
        try {
            clazz = classMap.get(className);

            // Get the static getInstance() method
            Method getInstanceMethod = clazz.getDeclaredMethod("getInstance");

            // Check if the method is static
            if (!java.lang.reflect.Modifier.isStatic(getInstanceMethod.getModifiers())) {
                throw new NoSuchMethodException("getInstance() method is not static in " + clazz.getName());
            }

            // Invoke the static method
            return getInstanceMethod.invoke(null); // null because it's a static method

        } catch (NoSuchMethodException e) {
            logger.warn("Error: getInstance() method not found in {}", clazz.getName());
            return null;
        } catch (IllegalAccessException e) {
            logger.warn("Error: Could not access getInstance() method in {}. Ensure it's public.", clazz.getName());
            return null;
        } catch (InvocationTargetException e) {
            logger.warn("Error: Exception occurred while invoking getInstance() method in {}", clazz.getName());
            return null;
        }
    }

}
