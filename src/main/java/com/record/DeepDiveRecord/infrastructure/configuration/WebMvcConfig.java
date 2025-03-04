package com.record.DeepDiveRecord.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Angular pruebas
                .allowedMethods("GET", "POST", "PUT","PATCH", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:80")  // Angular container
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
