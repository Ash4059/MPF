package com.example.MPF.Egresess;

import com.example.MPF.Model.Config;
import com.example.MPF.Model.Message;
import com.example.MPF.ModuleInterface.Egress;

public class RestEgress implements Egress {

    private static RestEgress instance;
    Config config;
    private boolean initialized = false;

    public RestEgress(Config config){
        this.initialize(config);
        instance = this;
    }

    @Override
    public void SendMessage(Message message) {

    }

    @Override
    public void initialize(Config config) {
        this.config = config;
        this.initialized = true;
    }

    @Override
    public void close() {
        this.initialized = false;
        instance = null;
    }

    public static RestEgress getInstance(){
        return instance;
    }
}
