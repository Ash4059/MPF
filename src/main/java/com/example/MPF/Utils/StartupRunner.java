package com.example.MPF.Utils;

import com.example.MPF.Model.Config;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class StartupRunner {

    private final ConfigLoader configLoader;

    public StartupRunner(ConfigLoader configLoader){
        this.configLoader = configLoader;
    }

    @PostConstruct
    public void run() throws IOException {

        Config config = configLoader.loadConfig(ConfigLoader.getConfigFile().getPath());

        List<Map<String, Object>> ingressConfigs = (List<Map<String, Object>>) config.get("ingresess");
        List<Map<String, Object>> egressConfigs = (List<Map<String, Object>>) config.get("egresess");

        for(Map<String,Object> ingressMap : ingressConfigs){
            String ingressType = (String) ingressMap.get("type");
            Map<String, Object> ingressProps = (Map<String, Object>) ingressMap.get("config");
            Config ingressConfig = new Config();
            ingressConfig.setProperties(ingressProps);
            ConnectorFactory.createIngress(ingressType, ingressConfig);
        }

        for(Map<String,Object> egressMap : egressConfigs){
            String egressType = (String) egressMap.get("type");
            Map<String, Object> egressProps = (Map<String, Object>) egressMap.get("config");
            Config egressConfig = new Config();
            egressConfig.setProperties(egressProps);
            ConnectorFactory.createEgress(egressType, egressConfig);
        }
    }
}
