package com.wgsistemas.motoboy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wgsistemas.motoboy.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	@EntityGraph(attributePaths = { "customer", "deliveredBy", "paymentMethod" })
	Page<Delivery> findAll(Pageable pageable);
}