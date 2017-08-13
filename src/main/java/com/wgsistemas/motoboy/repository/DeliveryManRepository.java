package com.wgsistemas.motoboy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wgsistemas.motoboy.model.DeliveryMan;

public interface DeliveryManRepository extends JpaRepository<DeliveryMan, Long> {
	Iterable<DeliveryMan> findByOwner_Username(String username, Sort sort);
	Page<DeliveryMan> findByOwner_Username(String username, Pageable pageable);
}