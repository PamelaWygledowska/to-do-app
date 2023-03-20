package com.pamela.todoapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Open api implemented based on
 * https://www.baeldung.com/spring-rest-openapi-documentation
 * https://www.baeldung.com/openapi-jwt-authentication
 * <p>
 * After change of swagger configuration sometimes it is required do restart browser
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "TO DO application API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Pamela Wyględowska", email = "pamelawygledowska@gmail.com"
                ),
                description = "API for for organising personal tasks."
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
class OpenAPIConfiguration {

}
