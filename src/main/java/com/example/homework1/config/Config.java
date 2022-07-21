package com.example.homework1.config;

import com.example.homework1.systemProfile.DevProfile;
import com.example.homework1.systemProfile.ProductionProfile;
import com.example.homework1.systemProfile.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    @ConditionalOnProperty(value = "devProfile", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(value = "devProfile", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
