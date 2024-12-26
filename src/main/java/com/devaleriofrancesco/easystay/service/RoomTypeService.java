package com.devaleriofrancesco.easystay.service;

import com.devaleriofrancesco.easystay.model.GalleriaImmagini;
import com.devaleriofrancesco.easystay.model.RoomType;
import com.devaleriofrancesco.easystay.model.Servizi;
import com.devaleriofrancesco.easystay.repository.GalleriaImmaginiRepository;
import com.devaleriofrancesco.easystay.repository.RoomTypeRepository;
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
    private final ServiziRepository serviziRepository;
    private final GalleriaImmaginiRepository galleryRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository, ServiziRepository serviziRepository, GalleriaImmaginiRepository galleryRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.serviziRepository = serviziRepository;
        this.galleryRepository = galleryRepository;
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

    public void bulkInsertFromJson(String jsonPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<RoomType>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream(jsonPath);

        List<RoomType> roomTypes = objectMapper.readValue(inputStream, typeReference);
        for (RoomType roomType : roomTypes) {

            // Persist servizi first
            List<Servizi> serviziList = roomType.getServizi();
            if (serviziList != null) {
                for (Servizi servizio : serviziList) {
                    Servizi existingServizio = serviziRepository.findFirstByNome(servizio.getNome());
                    if (existingServizio == null) {
                        serviziRepository.save(servizio);
                    } else {
                        servizio.setId(existingServizio.getId());
                    }
                }
            }

            // Persist room type
            roomTypeRepository.save(roomType);

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
