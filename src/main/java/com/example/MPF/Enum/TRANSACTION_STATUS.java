package com.example.MPF.Enum;

public enum TRANSACTION_STATUS {
    REST_API_RECIEVED(0),
    SEND_TO_KAFKA(1),
    RECIVED_FROM_KAFKA(2),
    SEND_TO_SQL(3),
    TRANSACTION_SUCCESSFULL(4),
    TRANSACTION_UNSUCCESSFULL(5);

    private final int value;

    TRANSACTION_STATUS(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
