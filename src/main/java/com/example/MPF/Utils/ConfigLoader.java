package com.example.MPF.Utils;

import com.example.MPF.Model.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@Component
public class ConfigLoader {

    private final ObjectMapper objectMapper;

    public ConfigLoader(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public Config loadConfig(String path){
        try(InputStream inputStream = new FileInputStream(path)){
            Map<String, Object> props = objectMapper.readValue(inputStream, new TypeReference<Map<String, Object>>() {});
            Config config = new Config();
            config.setProperties(props);
            return config;
        }catch (IOException e){
            throw new RuntimeException("Failed to load config file " + path, e);
        }
    }

    public static File getConfigFile() throws IOException {
        return new ClassPathResource("Config.json").getFile();
    }
}
