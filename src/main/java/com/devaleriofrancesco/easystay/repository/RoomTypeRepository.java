package com.devaleriofrancesco.easystay.repository;

import com.devaleriofrancesco.easystay.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    // get all roomtypes ordered by id asc
    java.util.List<RoomType> findAllByOrderByIdAsc();
}
