package com.record.DeepDiveRecord.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Aquí debes especificar los orígenes permitidos, por ejemplo, la URL donde se ejecuta tu aplicación Angular
                .allowedMethods("GET", "POST", "PUT","PATCH", "DELETE")
                .allowedHeaders("*");
    }
}
