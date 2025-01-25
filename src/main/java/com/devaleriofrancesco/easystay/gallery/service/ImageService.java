package com.devaleriofrancesco.easystay.gallery.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;

@Service
public class ImageService {

    @Value("${images.upload.dir}")
    private String uploadDir;

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

        Path uploadPath = getUploadPath();
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

    private Path getUploadPath() throws FileNotFoundException {
        // Get the project's root directory
        String projectRoot = System.getProperty("user.dir");
        // Resolve the uploads folder path
        Path uploadPath =  Paths.get(projectRoot, uploadDir, "roomtypes");
        // Create the uploads folder if it does not exist
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new FileNotFoundException("Upload directory not found");
            }
        }
        return uploadPath;
    }

    // delete image from file system
    public boolean deleteImage(String imageName) {
        try {
            Path imagePath = getUploadPath().resolve(imageName);
            Files.deleteIfExists(imagePath);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("Errore in fase di eliminazione immagine", e);
        }
    }

}
