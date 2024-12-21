package com.devaleriofrancesco.easystay.service;

import com.devaleriofrancesco.easystay.model.RoomType;
import com.devaleriofrancesco.easystay.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }

    public RoomType findById(Integer id) {
        return roomTypeRepository.findById(id).orElse(null);
    }

    public RoomType save(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public void deleteById(Integer id) {
        roomTypeRepository.deleteById(id);
    }
}
