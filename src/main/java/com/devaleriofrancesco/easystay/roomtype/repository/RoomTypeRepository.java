package com.devaleriofrancesco.easystay.roomtype.repository;

import com.devaleriofrancesco.easystay.roomtype.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    // get all roomtypes ordered by id asc
    java.util.List<RoomType> findAllByOrderByIdAsc();
}
