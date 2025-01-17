package com.devaleriofrancesco.easystay.controller.Admin;

import com.devaleriofrancesco.easystay.model.Room;
import com.devaleriofrancesco.easystay.model.RoomResponse;
import com.devaleriofrancesco.easystay.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/rooms")
public class AdminRoomController {

    private final RoomService roomService;

    // get all rooms
    @GetMapping("/page/{page}")
    public ResponseEntity<RoomResponse> getAllRooms(@PathVariable int page) {
        return ResponseEntity.ok(roomService.getAllRooms(page));
    }

    // delete room
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok().build();
    }

    // update room
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return ResponseEntity.ok(roomService.updateRoom(id, room));
    }

    // create room
    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.createRoom(room));
    }

}
