package com.example.project_shop.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationSpring {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
