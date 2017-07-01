package com.wgsistemas.motoboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wgsistemas.motoboy.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}