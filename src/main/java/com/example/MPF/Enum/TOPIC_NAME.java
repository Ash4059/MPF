package com.example.MPF.Enum;

public enum TOPIC_NAME {
    REGISTER_USER("register_user"),
    DELETE_USER("delete_user"),
    UPDATE_USER("update_user");

    private final String value;
    TOPIC_NAME(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}