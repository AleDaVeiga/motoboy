package com.wgsistemas.motoboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wgsistemas.motoboy.model.State;

public interface StateRepository extends JpaRepository<State, Long> {
	
	State findByAbbreviation(String abbreviation);
}