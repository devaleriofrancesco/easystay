package com.devaleriofrancesco.easystay.controller;

import com.devaleriofrancesco.easystay.model.RoomType;
import com.devaleriofrancesco.easystay.service.RoomTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roomtypes")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping
    public List<RoomType> findAll() {
        return roomTypeService.findAll();
    }

    @GetMapping("/{id}")
    public RoomType findById(@PathVariable Integer id) {
        return roomTypeService.findById(id);
    }

}
