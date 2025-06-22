package com.example.MPF.ModuleInterface;

import com.example.MPF.Model.Config;

public abstract class AbstractConnector {

    protected boolean initialized = false;
    protected Config config;

    public Config getConfig() {
        return this.config;
    }

}
