package com.praveen.expense_tracker_app.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Expense Tacker API")
                        .version("1.0")
                        .description("API documentation for Expense Tracker Application")
                        .contact(new Contact()
                                .name("Praveen")
                                .email("praveenhsn.gowda@gmail.com")
                                .url("https://github.com/Praveen-km07/Spring-Boot-Apache-Kafka/tree/main/expense-tracker-app")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.google.com/license")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("API documentation for Expense Tracker Application")
                        .url("https://www.google.com/external-doc")
                );
    }
}
