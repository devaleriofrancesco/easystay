package com.devaleriofrancesco.easystay.service;

import com.devaleriofrancesco.easystay.model.Booking;
import com.devaleriofrancesco.easystay.model.Room;
import com.devaleriofrancesco.easystay.repository.RoomRepository;
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

}
