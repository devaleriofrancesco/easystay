package com.devaleriofrancesco.easystay.gallery.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.Objects;

@Service
public class ImageService {

    @Value("${images.upload.dir}")
    private static String uploadDir;
    private static final String IMAGES = "images";
    private static final String ROOMTYPES_DIR = "roomtypes";
    private final Logger logger = LoggerFactory.getLogger(ImageService.class);

    // save image to file system from base64 data
    public String saveImage(String base64Image) throws FileNotFoundException {
        // Extract the image type (e.g., png, jpg) from the base64 string
        String[] parts = base64Image.split(",");
        String imageString = parts[1];
        String extension = parts[0].split("/")[1].split(";")[0];

        // Generate a unique file name
        String fileName = "image_" + System.currentTimeMillis() + "." + extension;

        // Decode the base64 string
        byte[] imageBytes = Base64.getDecoder().decode(imageString);

        Path uploadPath = getUploadPath(IMAGES, ROOMTYPES_DIR);
        Path filePath = uploadPath.resolve(fileName);
        // Save the image to the file system
        try {
            Files.write(filePath, imageBytes);
        } catch (IOException e) {
            throw new IllegalArgumentException("Errore in fase di salvataggio immagine", e);
        }

        // Return the path of the saved image
        return fileName;
    }

    public static Path getUploadPath(String... dirs) throws FileNotFoundException {
        Path uploadPath;
        uploadPath = Paths.get(Objects.requireNonNullElse(uploadDir, "uploads"), dirs);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new FileNotFoundException("Upload directory not found");
            }
        }
        return uploadPath.toAbsolutePath();
    }

    // delete image from file system
    public boolean deleteImage(String imageName) {
        try {
            Path imagePath = getUploadPath(IMAGES, ROOMTYPES_DIR).resolve(imageName);
            Files.deleteIfExists(imagePath);
            return true;
        } catch (Exception e) {
            logger.error("Errore in fase di cancellazione immagine", e);
            return false;
        }
    }

}
