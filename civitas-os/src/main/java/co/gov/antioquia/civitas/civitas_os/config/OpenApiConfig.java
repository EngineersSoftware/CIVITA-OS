package co.gov.antioquia.civitas.civitas_os.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI civitasOpenApi() {
        final String securitySchemeName = "BearerAuth";

        return new OpenAPI()
                            .info(new Info()
                                  .title("CivitasOS API")
                                  .description("API REST para la gestion integral de activos y tickets del departamento")
                                  .version("v1.0.0")
                                  .contact(new Contact()
                                  .name("Equipo de Desarrollo")
                                  .email("dev@civitas.gov.co"))
                                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
                
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                                            .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                            .name(securitySchemeName)
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")));
    }

}
