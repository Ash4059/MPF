package com.example.MPF.Config;

import com.example.MPF.Utils.CustomInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    CustomInterceptor customInterceptor;

    public AppConfig(CustomInterceptor customInterceptor){
        this.customInterceptor = customInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.customInterceptor)
                .addPathPatterns("/user");
    }
}
