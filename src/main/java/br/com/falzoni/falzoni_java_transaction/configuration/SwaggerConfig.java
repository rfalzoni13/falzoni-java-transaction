package br.com.falzoni.falzoni_java_transaction.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customApi() {
        return new OpenAPI(SpecVersion.V30)
                .info(new Info().title("Falzoni Transactions Spring Boot API")
                        .description("Api de demonstração de transações com Java 17 e Spring Boot 3")
                        .version("v1"))
                .addServersItem(new Server().url("").description("Empty"));
    }

    @Bean
    public OpenApiCustomizer hideServers() {
        return openApi -> {
            openApi.setServers(new ArrayList<>());
        };
    }
}
