package com.devaleriofrancesco.easystay.repository;

import com.devaleriofrancesco.easystay.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<Role, Integer> {
}
