package com.bookmarkstoreserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @Created 12/02/2022 - 11:32
 * @Package com.bookmarkstoreserver.config
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Configuration
@EnableSwagger2
//extends WebMvcConfigurationSupport
public class SwaggerConfig {

    @Bean
    public Docket apiDocket(){
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.bookmarkstoreserver"))
                .build()
                .apiInfo(apiInfo());
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Swagger API Documention For Bookmark Store Project",
                "API Reference By Ouakala Abdelaaziz",
                "1.0.0",
                "Public API",
                new Contact("Ouakala Abdelaaziz" ,
                        "https://github.com/O-Abdelaaziz",
                        "mr.ouakala.abdelaaziz@gmail.com"),
                "API License Open",
                "https://dz.linkedin.com/in/abdelaaziz-ouakala",
                Collections.emptyList()
        );
    }

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}
