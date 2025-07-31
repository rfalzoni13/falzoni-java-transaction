package br.com.falzoni.falzoni_java_transaction.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthConfig {
    @Bean
    public CustomStatusAggregator customStatusAggregator() {
        return new CustomStatusAggregator();
    }
}
