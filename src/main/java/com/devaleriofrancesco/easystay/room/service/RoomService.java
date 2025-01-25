package com.devaleriofrancesco.easystay.room.service;

import com.devaleriofrancesco.easystay.booking.model.Booking;
import com.devaleriofrancesco.easystay.room.model.Room;
import com.devaleriofrancesco.easystay.room.model.RoomResponse;
import com.devaleriofrancesco.easystay.roomtype.model.RoomType;
import com.devaleriofrancesco.easystay.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    // get all rooms
    public RoomResponse getAllRooms(int page) {
        Page<Room> pageRoom = roomRepository.findAllByOrderByNumeroStanzaAsc(
                PageRequest.of(--page, 5)
        );
        int totalPages = pageRoom.getTotalPages();
        return RoomResponse.builder()
                .pageNumber(++page)
                .totalPages(totalPages)
                .rooms(pageRoom.getContent())
                .build();
    }

    // save room
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    // delete room
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    // update room
    public Room updateRoom(Long id, Room room) {
        Room oldRoom = roomRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Stanza non trovata")
        );
        room.setNumeroStanza(oldRoom.getNumeroStanza());
        return roomRepository.save(room);
    }

    // create room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getNewRoom(Booking oldBooking, LocalDate newCheckIn, LocalDate newCheckOut) {
        Room room = oldBooking.getStanza();
        Page<Room> roomPage = roomRepository.findFirstAvailableRoom(
                PageRequest.of(0, 1),
                newCheckIn,
                newCheckOut,
                room.getTipoStanza(),
                oldBooking.getId()
        );

        Optional<Room> newRoom = roomPage.get().findFirst();
        return newRoom.orElseThrow(
                () -> new IllegalArgumentException("Non ci sono stanze disponibili per le date selezionate")
        );

    }

    public Room getNewRoom(LocalDate newCheckIn, LocalDate newCheckOut, RoomType roomType) {
        Page<Room> roomPage = roomRepository.findFirstAvailableRoom(
                PageRequest.of(0, 1),
                newCheckIn,
                newCheckOut,
                roomType
        );

        Optional<Room> newRoom = roomPage.get().findFirst();
        return newRoom.orElseThrow(
                () -> new IllegalArgumentException("Non ci sono stanze disponibili per le date selezionate")
        );

    }

}
