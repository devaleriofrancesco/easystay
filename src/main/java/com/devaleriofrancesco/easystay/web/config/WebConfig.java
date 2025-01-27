package com.devaleriofrancesco.easystay.web.config;

import com.devaleriofrancesco.easystay.gallery.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Map;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);

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

        Path uploadPath = null;
        try {
            uploadPath = ImageService.getUploadPath("images");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        logger.info("Images Upload path: " + uploadPath);
        logger.info("Images Upload path 2: " + System.getProperty("user.dir"));
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadPath);
    }
}
