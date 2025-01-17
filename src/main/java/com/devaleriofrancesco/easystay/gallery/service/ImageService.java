package com.devaleriofrancesco.easystay.gallery.service;


import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class ImageService {

    // save image to file system from base64 data
    public String saveImage(String base64Image) throws FileNotFoundException {
        // Define the directory where the image will be saved
        String directory;
        try {
            directory = ResourceUtils.getFile("classpath:static/images/roomtypes").getAbsolutePath() + "/";
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Directory not found", e);
        }

        // Extract the image type (e.g., png, jpg) from the base64 string
        String[] parts = base64Image.split(",");
        String imageString = parts[1];
        String extension = parts[0].split("/")[1].split(";")[0];

        // Generate a unique file name
        String fileName = "image_" + System.currentTimeMillis() + "." + extension;

        // Decode the base64 string
        byte[] imageBytes = Base64.getDecoder().decode(imageString);

        // Save the image to the file system
        try (FileOutputStream fos = new FileOutputStream(directory + fileName)) {
            fos.write(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Return the path of the saved image
        return fileName;
    }

    // delete image from file system
    public boolean deleteImage(String imagePath) {
        try {
            String directory = ResourceUtils.getFile("classpath:static/images/roomtypes").getAbsolutePath() + "/";
            java.io.File file = new java.io.File(directory + imagePath);
            if (file.delete()) {
                return true;
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Directory not found", e);
        }
        return false;
    }

}
