package com.devaleriofrancesco.easystay.service;

import com.devaleriofrancesco.easystay.model.GalleriaImmagini;
import com.devaleriofrancesco.easystay.model.RoomType;
import com.devaleriofrancesco.easystay.model.RoomTypeServizi;
import com.devaleriofrancesco.easystay.model.Servizi;
import com.devaleriofrancesco.easystay.repository.GalleriaImmaginiRepository;
import com.devaleriofrancesco.easystay.repository.RoomTypeRepository;
import com.devaleriofrancesco.easystay.repository.RoomTypeServiziRepository;
import com.devaleriofrancesco.easystay.repository.ServiziRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeServiziRepository roomTypeServiziRepository;
    private final GalleriaImmaginiRepository galleryRepository;
    private final ServiziRepository serviziRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository, RoomTypeServiziRepository roomTypeServiziRepository, GalleriaImmaginiRepository galleryRepository, ServiziRepository serviziRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.roomTypeServiziRepository = roomTypeServiziRepository;
        this.galleryRepository = galleryRepository;
        this.serviziRepository = serviziRepository;
    }


    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }

    public RoomType findById(Integer id) {
        return roomTypeRepository.findById(id).orElse(null);
    }

    public RoomType addRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public RoomType updateRoomType(Integer id, RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public void deleteRoomType(Integer id) {
        roomTypeRepository.deleteById(id);
    }

    public void deleteById(Integer id) {
        roomTypeRepository.deleteById(id);
    }

    public void bulkInsertFromJson(String jsonPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<RoomType>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream(jsonPath);

        List<RoomType> roomTypes = objectMapper.readValue(inputStream, typeReference);
        for (RoomType roomType : roomTypes) {

            // Persist servizi first
            List<RoomTypeServizi> serviziList = roomType.getRoomTypeServizi();
            if (serviziList != null) {
                for (RoomTypeServizi roomTypeServizio : serviziList) {
                    Servizi servizio = roomTypeServizio.getServizio();
                    Servizi existingServizio = serviziRepository.findFirstByNome(servizio.getNome());
                    if (existingServizio == null) {
                        serviziRepository.save(servizio);
                    } else {
                        roomTypeServizio.setServizio(existingServizio);
                    }
                }
            }

            // Persist room type
            roomTypeRepository.save(roomType);

            // Persist roomTypeServizi
            if (serviziList != null) {
                for (RoomTypeServizi roomTypeServizio : serviziList) {
                    roomTypeServizio.setRoomType(roomType);
                    roomTypeServiziRepository.save(roomTypeServizio);
                }
            }

            // Persist galleries
            List<GalleriaImmagini> galleries = roomType.getGalleriaImmagini();
            if (galleries != null) {
                for (GalleriaImmagini gallery : galleries) {
                    gallery.setRoomType(roomType);
                }
                galleryRepository.saveAll(galleries);
            }
        }
    }

}
