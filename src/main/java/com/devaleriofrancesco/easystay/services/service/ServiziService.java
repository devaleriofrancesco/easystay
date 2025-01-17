package com.devaleriofrancesco.easystay.services.service;

import com.devaleriofrancesco.easystay.roomtype.model.RoomType;
import com.devaleriofrancesco.easystay.roomtype.model.RoomTypeServizi;
import com.devaleriofrancesco.easystay.roomtype.service.RoomTypeService;
import com.devaleriofrancesco.easystay.services.model.Servizi;
import com.devaleriofrancesco.easystay.roomtype.repository.RoomTypeServiziRepository;
import com.devaleriofrancesco.easystay.services.repository.ServiziRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiziService {

    private final RoomTypeServiziRepository roomTypeServiziRepository;
    private final ServiziRepository serviziRepository;
    private final RoomTypeService roomTypeService;

    public List<Servizi> getAllServices() {
        return serviziRepository.findAll();
    }

    // update service
    public Servizi updateService(Integer id, Servizi servizi) {
        Servizi service = serviziRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Servizio non trovato"));
        servizi.setId(service.getId());
        return serviziRepository.save(servizi);
    }

    // create service
    public Servizi createService(Servizi servizi) {
        return serviziRepository.save(servizi);
    }

    // delete service
    public void deleteService(Integer id) {
        serviziRepository.deleteById(id);
    }

    public List<RoomTypeServizi> getAllServicesForRoomType(Integer id) {
        RoomType roomType = roomTypeService.findById(id);
        List<Servizi> availableServices;
        if (roomType == null) {
            availableServices = serviziRepository.findAll();
        }else {
            availableServices =  serviziRepository.findAllServicesNotAssociatedWithRoomType(roomType);
        }

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
