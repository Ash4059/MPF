package com.example.MPF.ModuleInterface;

import com.example.MPF.Model.Config;
import com.example.MPF.Model.Message;

public abstract class Egress extends AbstractConnector {

    public void SendMessage(Message message){

    }

    public abstract void initialize(Config config);

    public void close(){}
}
