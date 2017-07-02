package com.wgsistemas.motoboy.service;

import com.wgsistemas.motoboy.model.DeliveryMan;

public interface DeliveryManService {
	DeliveryMan create(DeliveryMan deliveryMan, String username);

	DeliveryMan update(DeliveryMan deliveryMan, String username);

	void remove(DeliveryMan deliveryMan);

	DeliveryMan findOne(Long id);

	Iterable<DeliveryMan> findAll();
}