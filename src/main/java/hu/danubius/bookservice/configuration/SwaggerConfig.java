package hu.danubius.bookservice.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bookServiceOpenApiConfig() {
        return new OpenAPI()
            .components(
                new Components()
                    .addSecuritySchemes("basicAuth",
                            new SecurityScheme()
                                .scheme("basic")
                                .type(SecurityScheme.Type.HTTP)
                        )
            )
            .info(
                new Info()
                    .title("Book Service API")
                    .version("v1")
            )
            .addSecurityItem(
                new SecurityRequirement()
                    .addList("basicAuth")
            );
    }
}
