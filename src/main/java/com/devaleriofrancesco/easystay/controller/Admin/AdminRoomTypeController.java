package com.devaleriofrancesco.easystay.controller.Admin;

import com.devaleriofrancesco.easystay.model.RoomType;
import com.devaleriofrancesco.easystay.model.RoomTypeRequest;
import com.devaleriofrancesco.easystay.service.RoomTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/roomtypes")
public class AdminRoomTypeController {

    private final RoomTypeService roomTypeService;

    // add room type
    @PostMapping
    public ResponseEntity<RoomType> addRoomType(@Valid @RequestBody RoomTypeRequest roomType) {
        return ResponseEntity.ok(roomTypeService.addRoomType(roomType));
    }

    // update room type
    @PutMapping("/{id}")
    public ResponseEntity<RoomType> updateRoomType(@PathVariable Integer id, @Valid @RequestBody RoomTypeRequest roomType) {
        return ResponseEntity.ok(roomTypeService.updateRoomType(id, roomType));
    }

    // delete room type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable Integer id) {
        roomTypeService.deleteRoomType(id);
        return ResponseEntity.ok().build();
    }

    // get empty object
    @GetMapping("/new")
    public ResponseEntity<RoomType> getNewRoomType() {
        return ResponseEntity.ok(roomTypeService.getNewRoomType());
    }

}
