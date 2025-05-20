package com.example.MPF.ModuleInterface;

import com.example.MPF.Model.Config;
import com.example.MPF.Model.Message;

public interface Ingress {
    Message fetch();
    void initialize(Config config);
    void close();
}
