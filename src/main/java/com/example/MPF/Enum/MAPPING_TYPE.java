package com.example.MPF.Enum;

public enum MAPPING_TYPE {

    GET_MAPPING(0),
    POST_MAPPING(1),
    PUT_MAPPING(2),
    DELETE_MAPPING(3);

    private int value;

    MAPPING_TYPE(int value){
        this.value = value;
    }

}
