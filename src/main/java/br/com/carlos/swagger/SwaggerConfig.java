package br.com.carlos.swagger;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@Configuration

public class SwaggerConfig {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
                
    }private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Tarefas API REST",
                "API REST de gerenciamento de tarefas.",
                "1.0",
                "",
                new Contact("Carlos Henriqued de Andrade Sobrinho", "https://github.com/carlos0406",
                        "carlos.andrade.henrique1@outlook.com"),
                "",
                "", new ArrayList()
        );

        return apiInfo;
    }

}
