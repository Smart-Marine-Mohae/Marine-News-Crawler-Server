package ict.smartmarine.news.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().addServersItem(new Server().url("/"))
                .components(
                        new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("Marine News Swagger Open API")
                        .version("v1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("<http://springdoc.org>"))
                );
    }
}