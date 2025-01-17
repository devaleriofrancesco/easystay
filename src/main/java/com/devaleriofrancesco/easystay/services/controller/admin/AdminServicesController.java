package com.devaleriofrancesco.easystay.services.controller.admin;

import com.devaleriofrancesco.easystay.roomtype.model.RoomTypeServizi;
import com.devaleriofrancesco.easystay.services.model.Servizi;
import com.devaleriofrancesco.easystay.services.service.ServiziService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/services")
public class AdminServicesController {

    private final ServiziService roomTypeService;

    // get all services
    @GetMapping
    public ResponseEntity<List<Servizi>> getAllServices() {
        return ResponseEntity.ok(roomTypeService.getAllServices());
    }

    // get all available services for a room type
    @GetMapping("/roomtype/{id}")
    public ResponseEntity<List<RoomTypeServizi>> getAllServicesForRoomType(@PathVariable Integer id) {
        return ResponseEntity.ok(roomTypeService.getAllServicesForRoomType(id));
    }

    // update service
    @PutMapping("/{id}")
    public ResponseEntity<Servizi> updateService(@PathVariable Integer id, @RequestBody Servizi servizi) {
        return ResponseEntity.ok(roomTypeService.updateService(id, servizi));
    }

    // create service
    @PostMapping
    public ResponseEntity<Servizi> createService(@RequestBody Servizi servizi) {
        return ResponseEntity.ok(roomTypeService.createService(servizi));
    }

    // delete service
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        roomTypeService.deleteService(id);
        return ResponseEntity.ok().build();
    }

}
