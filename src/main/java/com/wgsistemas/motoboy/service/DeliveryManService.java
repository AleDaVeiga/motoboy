package com.wgsistemas.motoboy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wgsistemas.motoboy.model.DeliveryMan;

public interface DeliveryManService {
	DeliveryMan create(DeliveryMan deliveryMan, String username);

	DeliveryMan update(DeliveryMan deliveryMan, String username);

	void remove(DeliveryMan deliveryMan);

	DeliveryMan findOne(Long id);

	@Deprecated
	Iterable<DeliveryMan> findAll();

	Page<DeliveryMan> findAll(Pageable pageable);
}