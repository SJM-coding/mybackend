package com.basic.myspringboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test") //configuration 식별자(application.properties에서 설정한)
public class TestConfig {
    @Bean
    public CustomerVO customerVO(){

        return CustomerVO.builder() //CustomerVOBuilder
                .mode("테스트환경")
                .rate(1.5)
                .build();
    }
}
