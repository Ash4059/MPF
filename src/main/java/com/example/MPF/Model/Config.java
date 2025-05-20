package com.example.MPF.Model;

import java.util.Map;

public class Config {

    Map<String, Object> properties;

    public Config(){}

    public void setProperties(Map<String, Object> properties){
        this.properties = properties;
    }

    public Object get(String type){
        return properties.get(type);
    }

}
