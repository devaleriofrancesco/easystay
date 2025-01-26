package com.devaleriofrancesco.easystay.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false)
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .defaultContentType(MediaType.APPLICATION_OCTET_STREAM)
                .mediaTypes(Map.of(
                        "webp", MediaType.parseMediaType("image/webp"),
                        "png", MediaType.IMAGE_PNG,
                        "jpg", MediaType.parseMediaType("image/jpeg"),
                        "jpeg", MediaType.parseMediaType("image/jpeg"),
                        "gif", MediaType.parseMediaType("image/gif")
                ));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/images/");
    }
}
