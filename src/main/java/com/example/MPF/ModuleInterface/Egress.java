package com.example.MPF.ModuleInterface;

import com.example.MPF.Model.Config;
import com.example.MPF.Model.Message;

public interface Egress {
    void SendMessage(Message message);
    void initialize(Config config);
    void close();
}
