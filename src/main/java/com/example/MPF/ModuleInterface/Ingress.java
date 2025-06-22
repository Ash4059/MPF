package com.example.MPF.ModuleInterface;

import com.example.MPF.Model.Config;
import com.example.MPF.Model.Message;

public abstract class Ingress extends AbstractConnector {

    public Message fetch(){
        return null;
    }

    public abstract void initialize(Config config);

    public abstract void close();
}
