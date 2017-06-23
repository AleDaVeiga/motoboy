package com.wgsistemas.motoboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wgsistemas.motoboy.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}