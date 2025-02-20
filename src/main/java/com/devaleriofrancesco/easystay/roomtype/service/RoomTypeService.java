package com.devaleriofrancesco.easystay.roomtype.service;

import com.devaleriofrancesco.easystay.gallery.model.GalleriaImmagini;
import com.devaleriofrancesco.easystay.gallery.service.ImageService;
import com.devaleriofrancesco.easystay.gallery.repository.GalleriaImmaginiRepository;
import com.devaleriofrancesco.easystay.room.model.Room;
import com.devaleriofrancesco.easystay.room.service.RoomService;
import com.devaleriofrancesco.easystay.roomtype.repository.RoomTypeRepository;
import com.devaleriofrancesco.easystay.roomtype.repository.RoomTypeServiziRepository;
import com.devaleriofrancesco.easystay.roomtype.model.RoomType;
import com.devaleriofrancesco.easystay.roomtype.model.RoomTypeRequest;
import com.devaleriofrancesco.easystay.roomtype.model.RoomTypeServizi;
import com.devaleriofrancesco.easystay.services.repository.ServiziRepository;
import com.devaleriofrancesco.easystay.services.model.Servizi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeServiziRepository roomTypeServiziRepository;
    private final RoomService roomService;
    private final GalleriaImmaginiRepository galleryRepository;
    private final ServiziRepository serviziRepository;
    private final ImageService imageService;

    public RoomTypeService(RoomTypeRepository roomTypeRepository, RoomTypeServiziRepository roomTypeServiziRepository, RoomService roomService, GalleriaImmaginiRepository galleryRepository, ServiziRepository serviziRepository, ImageService imageService) {
        this.roomTypeRepository = roomTypeRepository;
        this.roomTypeServiziRepository = roomTypeServiziRepository;
        this.roomService = roomService;
        this.galleryRepository = galleryRepository;
        this.serviziRepository = serviziRepository;
        this.imageService = imageService;
    }


    public List<RoomType> findAll() {
        return roomTypeRepository.findAllByOrderByIdAsc();
    }

    public RoomType findById(Integer id) {
        return roomTypeRepository.findById(id).orElse(null);
    }

    public RoomType addRoomType(RoomTypeRequest roomType) {
        RoomType newRoomType = getNewRoomType();
        newRoomType.setId(null);
        newRoomType.setNome(roomType.getNome());
        newRoomType.setDescrizione(roomType.getDescrizione());
        newRoomType.setMetriQuadri(roomType.getMetriQuadri());
        newRoomType.setPrezzo(roomType.getPrezzo());
        newRoomType.setNumeroAdulti(roomType.getNumeroAdulti());
        newRoomType.setNumeroBambini(roomType.getNumeroBambini());
        newRoomType = roomTypeRepository.save(newRoomType);

        // manage services
        manageServices(roomType, newRoomType);

        // manage images
        manageImages(roomType, newRoomType);

        return newRoomType;
    }

    public RoomType updateRoomType(Integer id, RoomTypeRequest roomTypeRequest) {
        RoomType roomTypeToUpdate = roomTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tipo stanza non trovato"));
        roomTypeToUpdate.setNome(roomTypeRequest.getNome());
        roomTypeToUpdate.setDescrizione(roomTypeRequest.getDescrizione());
        roomTypeToUpdate.setPrezzo(roomTypeRequest.getPrezzo());
        roomTypeToUpdate.setNumeroAdulti(roomTypeRequest.getNumeroAdulti());
        roomTypeToUpdate.setNumeroBambini(roomTypeRequest.getNumeroBambini());
        roomTypeToUpdate.setMetriQuadri(roomTypeRequest.getMetriQuadri());
        // manage servizi
        manageServices(roomTypeRequest, roomTypeToUpdate);

        // manage images
        manageImages(roomTypeRequest, roomTypeToUpdate);
        roomTypeRepository.save(roomTypeToUpdate);
        return roomTypeToUpdate;
    }

    private void manageServices(RoomTypeRequest roomTypeRequest, RoomType roomTypeToUpdate) {
        roomTypeRequest.getServizi().forEach(servizio -> {
            RoomTypeServizi roomTypeServizi = roomTypeServiziRepository.findById(servizio.getId()).orElse(null);
            if (roomTypeServizi == null) {
                roomTypeServizi = new RoomTypeServizi();
            }

            // handle remove associated service
            if (!servizio.isChecked()) {
                if (roomTypeServizi.getId() != null) {
                    roomTypeServiziRepository.delete(roomTypeServizi);
                }
                return;
            }

            roomTypeServizi.setQty(servizio.getQty());
            Servizi s = serviziRepository.findById(servizio.getServizio().getId()).orElseThrow(() -> new IllegalArgumentException("Servizio non trovato"));
            roomTypeServizi.setServizio(s);
            roomTypeServizi.setRoomType(roomTypeToUpdate);
            roomTypeServiziRepository.save(roomTypeServizi);
        });
    }

    private void manageImages(RoomTypeRequest roomTypeRequest, RoomType roomTypeToUpdate) {
        List<GalleriaImmagini> images = roomTypeRequest.getGalleriaImmagini();
        for (GalleriaImmagini image : images) {
            GalleriaImmagini imageToUpdate = galleryRepository.findById(image.getId()).orElse(null);
            if (imageToUpdate == null) {
                imageToUpdate = new GalleriaImmagini();
            }

            // handle image deletion
            if (image.getPosizione() == -1) {
                imageService.deleteImage(imageToUpdate.getPathImmagine());
                galleryRepository.delete(imageToUpdate);
                roomTypeToUpdate.getGalleriaImmagini().remove(imageToUpdate);
                continue;
            }

            if (image.getPathImmagine().startsWith("data:image")) {
                // convert base64 to image and save into file system
                String imagePath;
                try {
                    imagePath = imageService.saveImage(image.getPathImmagine());
                } catch (FileNotFoundException e) {
                    throw new IllegalArgumentException("Errore nel salvataggio dell'immagine");
                }

                // set image path and position
                imageToUpdate.setPathImmagine(imagePath);
                imageToUpdate.setPosizione(image.getPosizione());
            }

            imageToUpdate.setRoomType(roomTypeToUpdate);
            galleryRepository.save(imageToUpdate);
        }
    }

    public RoomType getNewRoomType() {
        RoomType roomType = new RoomType();
        roomType.setId(0);
        roomType.setRoomTypeServizi(List.of());
        roomType.setGalleriaImmagini(List.of());
        return roomType;
    }

    public void deleteRoomType(Integer id) {
        // check if at least one room type exists
        if (roomTypeRepository.count() == 1) {
            throw new IllegalArgumentException("Impossibile eliminare l'ultima tipologia di stanza");
        }
        roomTypeRepository.deleteById(id);
    }

    public void deleteById(Integer id) {
        roomTypeRepository.deleteById(id);
    }

    public void bulkInsertFromJson(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<RoomType>> typeReference = new TypeReference<>() {
        };

        List<RoomType> roomTypes = objectMapper.readValue(inputStream, typeReference);
        for (RoomType roomType : roomTypes) {

            List<RoomTypeServizi> serviziList = roomType.getRoomTypeServizi();
            List<GalleriaImmagini> galleries = roomType.getGalleriaImmagini();

            roomType.setRoomTypeServizi(List.of());
            roomType.setGalleriaImmagini(List.of());
            // Persist room type
            roomTypeRepository.save(roomType);

            // Persist servizi first
            if (serviziList != null) {
                for (RoomTypeServizi roomTypeServizio : serviziList) {
                    Servizi servizio = roomTypeServizio.getServizio();
                    Servizi existingServizio = serviziRepository.findFirstByNome(servizio.getNome());
                    if (existingServizio == null) {
                        serviziRepository.save(servizio);
                        roomTypeServizio.setServizio(servizio);
                    } else {
                        roomTypeServizio.setServizio(existingServizio);
                    }

                    roomTypeServizio.setRoomType(roomType);
                    roomTypeServiziRepository.save(roomTypeServizio);
                }
            }

            // Persist galleries
            if (galleries != null) {
                for (GalleriaImmagini gallery : galleries) {
                    gallery.setRoomType(roomType);
                }
                galleryRepository.saveAll(galleries);
            }

            // persist rooms
            if (roomType.getStanze() != null) {
                for (Room room : roomType.getStanze()) {
                    room.setTipoStanza(roomType);
                    roomService.saveRoom(room);
                }
            }
        }
    }

}
