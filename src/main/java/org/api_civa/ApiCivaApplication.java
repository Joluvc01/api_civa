package org.api_civa;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class ApiCivaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCivaApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info((new Info()
                        .title("Reto Técnico Practicante Desarrollador FullStack")
                        .description("API para el manejo de buses, marca de buses y usuarios de la empresa CIVA")
                        .version("1.0.0")));
    }

}
