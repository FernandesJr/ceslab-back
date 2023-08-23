package br.com.ceslab.ceslab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class SecurityCorsConfig {

    @Autowired
    private Environment environment;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        String[] ORIGIN_CORS = new String[2];
        ORIGIN_CORS[0] = "https://ceslab.com.br";

        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            ORIGIN_CORS[1] = "http://localhost:4200";
            System.out.println("Modo test reconhecido autorizando: " + ORIGIN_CORS[1]);
        }

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(ORIGIN_CORS);
            }
        };
    }
}
