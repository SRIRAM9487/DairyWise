package com.DairyWise.backend.User.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("DWise")
            .description("Backend API for my app")
            .version("V0.1")
            .contact(new Contact().name("SRIRAM").url("https://github.com/SRIRM9487"))
            .license(new License().name("License").url("/")))
        .externalDocs(new ExternalDocumentation().description("Dwise")
            .url("http://localhost:8080/swagger-ui/index.html"))
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth")) 
        .components(new Components()
            .addSecuritySchemes("bearerAuth",
                new SecurityScheme()
                    .name("bearerAuth")
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT"))); 
  }
}

