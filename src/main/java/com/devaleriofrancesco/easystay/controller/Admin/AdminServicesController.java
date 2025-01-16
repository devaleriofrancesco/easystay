package com.devaleriofrancesco.easystay.controller.Admin;

import com.devaleriofrancesco.easystay.model.RoomTypeServizi;
import com.devaleriofrancesco.easystay.service.ServiziService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/services")
public class AdminServicesController {

    private final ServiziService roomTypeService;

    // get all services
    @GetMapping
    public ResponseEntity<List<RoomTypeServizi>> getAllServices() {
        return ResponseEntity.ok(roomTypeService.getAllServices());
    }

    // get all available services for a room type
    @GetMapping("/roomtype/{id}")
    public ResponseEntity<List<RoomTypeServizi>> getAllServicesForRoomType(@PathVariable Integer id) {
        return ResponseEntity.ok(roomTypeService.getAllServicesForRoomType(id));
    }

}
