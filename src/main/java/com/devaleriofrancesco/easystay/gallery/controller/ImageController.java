package com.devaleriofrancesco.easystay.gallery.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor
@RequestMapping("/api/images/roomtypes")
public class ImageController {

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws FileNotFoundException {
        URL resourceUrl = getClass().getClassLoader().getResource("static/images/roomtypes");
        try{
            Path imagePath = Paths.get(resourceUrl.toURI()).resolve(imageName);
            Resource resource = new UrlResource(imagePath.toUri());
            return ResponseEntity.ok()
                    .body(resource);
        }catch (Exception e){
            throw new FileNotFoundException("File not found");
        }
    }

}
