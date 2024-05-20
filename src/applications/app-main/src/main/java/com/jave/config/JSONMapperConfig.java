package com.jave.config;

import com.jave.JSONMapper;
import com.jave.JSONMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JSONMapperConfig {

    @Bean
    @Primary
    public JSONMapper jsonMapper() {
        return new JSONMapperImpl();
    }
}
