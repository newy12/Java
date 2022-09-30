package com.summar.member.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI initOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Summar Gateway API")
                        .description("Summar Gateway API 명세서입니다.")
                        .version("v0.0.1"))
                .components(
                        new Components().addSecuritySchemes(
                                "Authorization",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT").name("Authorization")
                        ));
    }

}
