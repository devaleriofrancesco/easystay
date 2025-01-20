package com.devaleriofrancesco.easystay.initialization;

import com.devaleriofrancesco.easystay.roomtype.service.RoomTypeService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


@Component
public class EntitiesLoader {

    private final RoomTypeService roomTypeService;
    Logger logger = LoggerFactory.getLogger(EntitiesLoader.class);

    public EntitiesLoader(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @PostConstruct
    public void loadEntities() {
        loadRoomTypes();
    }

    private void loadRoomTypes() {
        if (roomTypeService.findAll().isEmpty()) {
            try {
                InputStream resource;
                try {
                    resource = new ClassPathResource("json/roomtypes.json").getInputStream();
                } catch (FileNotFoundException e) {
                    throw new IllegalArgumentException("Directory not found", e);
                }

                roomTypeService.bulkInsertFromJson(resource);
            } catch (IOException e) {
                logger.error("Error loading room types", e);
            }
        }
    }

}
