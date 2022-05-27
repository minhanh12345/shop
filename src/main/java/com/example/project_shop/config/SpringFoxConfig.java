package com.example.project_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        List<SecurityScheme> securitySchemeList = new ArrayList<>();
        securitySchemeList.add(apiKey());

        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(securitySchemeList)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.project_shop.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("")
                .globalRequestParameters(Collections.singletonList(secretKeyParameter()));

    }
    private RequestParameter secretKeyParameter() {
        RequestParameterBuilder tokenBuilder = new RequestParameterBuilder();
        tokenBuilder
                .name("secretKey")
                .description("Secret Key")
                .required(true)
                .in("header")
                .accepts(Collections.singleton(MediaType.APPLICATION_JSON))
                .build();
        return tokenBuilder.build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("JWT", authorizationScopes));
        return securityReferences;
    }
}
