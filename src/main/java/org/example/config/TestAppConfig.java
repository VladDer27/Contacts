package org.example.config;

import org.example.EnvParser;
import org.example.TestParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-test.properties")
@Profile("test")
public class TestAppConfig {

    @Bean
    public EnvParser envParser() {
        return new TestParser();
    }
}
