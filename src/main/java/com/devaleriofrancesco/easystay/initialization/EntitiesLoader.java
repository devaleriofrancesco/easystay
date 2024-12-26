package com.devaleriofrancesco.easystay.initialization;

import com.devaleriofrancesco.easystay.service.RoomTypeService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;


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
                roomTypeService.bulkInsertFromJson("/json/roomTypes.json");
            } catch (IOException e) {
                logger.error("Error loading room types", e);
            }
        }
    }

}
