package com.example.MPF.Egresess;

import com.example.MPF.Model.Config;
import com.example.MPF.Model.Message;
import com.example.MPF.ModuleInterface.Egress;

public class SqlEgress implements Egress {

    private static SqlEgress instance;
    Config config;
    private boolean initialized = false;

    public SqlEgress(Config config){
        this.initialize(config);
        instance = this;
    }

    @Override
    public void SendMessage(Message message) {

    }

    @Override
    public void initialize(Config config) {
        this.config = config;
        initialized = true;
    }

    @Override
    public void close() {
        instance = null;
        initialized = false;
    }

    public static SqlEgress getInstance(){
        return instance;
    }
}
