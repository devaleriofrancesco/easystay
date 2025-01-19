package com.devaleriofrancesco.easystay;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class EasyStayApplication {

    private final Environment env;

    public EasyStayApplication(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        SpringApplication.run(EasyStayApplication.class, args);
    }

    // expose static assets folder to the web
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            }
        };
    }

    @PostConstruct
    public void init() {
        try {
            String secretKeyPath = env.getProperty("security.jwt.secret-key-path", "jwt-secret.key");
            Path path = Paths.get(secretKeyPath);
            if (!Files.exists(path)) {
                SecureRandom secureRandom = new SecureRandom();
                byte[] key = new byte[32];
                secureRandom.nextBytes(key);
                String encodedKey = Base64.getEncoder().encodeToString(key);
                Files.write(path, encodedKey.getBytes());
                System.out.println("Generated JWT secret key and saved to " + secretKeyPath);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT secret key", e);
        }
    }

}
