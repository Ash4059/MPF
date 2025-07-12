package com.example.MPF.Utils;

import com.example.MPF.Ingresess.RestIngress;
import com.example.MPF.Model.Config;
import com.example.MPF.ModuleInterface.AbstractConnector;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RestAspect {

    private final ClassRegistry classRegistry;
    private static final Logger logger = LoggerFactory.getLogger(RestAspect.class);

    public RestAspect(ClassRegistry classRegistry){
        this.classRegistry = classRegistry;
    }

    @After("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void PostRestCall(){
        RestIngress restIngress = RestIngress.getInstance();
        Config restIngressConfig = restIngress.getConfig();
        AbstractConnector abstractConnector = (AbstractConnector) this.classRegistry.getInstance(restIngressConfig.get("mapping").toString());
        logger.info("Post rest called");
    }

}
