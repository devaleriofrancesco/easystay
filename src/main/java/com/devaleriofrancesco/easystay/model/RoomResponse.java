package com.devaleriofrancesco.easystay.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class RoomResponse {
    private Integer pageNumber;
    private Integer totalPages;
    private List<Room> rooms;
}
