package com.bytmasoft.dss.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final AppPropertiesConfig appPropertiesConfig;

    @Override
    public void run(String... args) throws Exception {
        if(appPropertiesConfig.getInitData().isInitialize()){

        }
    }
}
