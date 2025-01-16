package com.devaleriofrancesco.easystay.service;

import com.devaleriofrancesco.easystay.model.RoomType;
import com.devaleriofrancesco.easystay.model.RoomTypeServizi;
import com.devaleriofrancesco.easystay.model.Servizi;
import com.devaleriofrancesco.easystay.repository.RoomTypeServiziRepository;
import com.devaleriofrancesco.easystay.repository.ServiziRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiziService {

    private final RoomTypeServiziRepository roomTypeServiziRepository;
    private final ServiziRepository serviziRepository;
    private final RoomTypeService roomTypeService;

    public List<RoomTypeServizi> getAllServices() {
        return roomTypeServiziRepository.findAll();
    }

    public List<RoomTypeServizi> getAllServicesForRoomType(Integer id) {
        RoomType roomType = roomTypeService.findById(id);
        if (roomType == null) {
            throw new IllegalArgumentException("Tipo stanza non trovato");
        }
        List<Servizi> availableServices =  serviziRepository.findAllServicesNotAssociatedWithRoomType(roomType);
        List<RoomTypeServizi> roomTypeServices = new java.util.ArrayList<>(List.of());
        availableServices.forEach(servizio -> {
            RoomTypeServizi roomTypeServizi = new RoomTypeServizi();
            roomTypeServizi.setId(0);
            roomTypeServizi.setQty(1);
            roomTypeServizi.setRoomType(roomType);
            roomTypeServizi.setServizio(servizio);
            roomTypeServices.add(roomTypeServizi);
        });
        return roomTypeServices;
    }
}
