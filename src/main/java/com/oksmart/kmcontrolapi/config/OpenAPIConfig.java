// Caminho: src/main/java/com/oksmart/kmcontrolapi/config/OpenAPIConfig.java

package com.oksmart.kmcontrolapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("KM Control API")
                        .version("1.0")
                        .description("API REST para gerenciamento de veículos e controle de quilometragem."));
    }
}
