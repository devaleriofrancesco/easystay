package com.devaleriofrancesco.easystay.controller.Admin;

import com.devaleriofrancesco.easystay.model.RoomType;
import com.devaleriofrancesco.easystay.service.RoomTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/roomtype")
public class AdminRoomTypeController {

    private final RoomTypeService roomTypeService;

    // get all room types
    @GetMapping
    public ResponseEntity<List<RoomType>> getAllRoomTypes() {
        return ResponseEntity.ok(roomTypeService.findAll());
    }

    // get room type by id
    @GetMapping("/{id}")
    public ResponseEntity<RoomType> getRoomTypeById(@PathVariable Integer id) {
        return ResponseEntity.ok(roomTypeService.findById(id));
    }

    // add room type
    @PostMapping
    public ResponseEntity<RoomType> addRoomType(@RequestBody RoomType roomType) {
        return ResponseEntity.ok(roomTypeService.addRoomType(roomType));
    }

    // update room type
    @PutMapping("/{id}")
    public ResponseEntity<RoomType> updateRoomType(@PathVariable Integer id, @RequestBody RoomType roomType) {
        return ResponseEntity.ok(roomTypeService.updateRoomType(id, roomType));
    }

    // delete room type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable Integer id) {
        roomTypeService.deleteRoomType(id);
        return ResponseEntity.ok().build();
    }

////    // get all services
////    @GetMapping("/services")
////    public ResponseEntity<List<Servizi>> getAllServices() {
////        return ResponseEntity.ok(roomTypeService.getAllServices());
////    }
////
////    // add service
////    @PostMapping("/services")
////    public ResponseEntity<Servizi> addService(@RequestBody Servizi servizi) {
////        return ResponseEntity.ok(roomTypeService.addService(servizi));
////    }
////
////    // update service
////    @PutMapping("/services/{id}")
////    public ResponseEntity<Servizi> updateService(@PathVariable Integer id, @RequestBody Servizi servizi) {
////        return ResponseEntity.ok(roomTypeService.updateService(id, servizi));
////    }
////
////    // delete service
////    @DeleteMapping("/services/{id}")
////    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
////        roomTypeService.deleteService(id);
////        return ResponseEntity.ok().build();
////    }
//
//    // get all room type services
//    @GetMapping("/roomtypeservices")
//    public ResponseEntity<List<RoomTypeServizi>> getAllRoomTypeServices() {
//        return ResponseEntity.ok(roomTypeService.getAllRoomTypeServices());
//    }
//
//    // add room type service
//    @PostMapping("/roomtypeservices")
//    public ResponseEntity<RoomTypeServizi> addRoomTypeService(@RequestBody RoomTypeServizi

}
