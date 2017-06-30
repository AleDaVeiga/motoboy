package com.wgsistemas.motoboy.service;

import com.wgsistemas.motoboy.model.DeliveryMan;

public interface DeliveryManService {
	DeliveryMan create(DeliveryMan deliveryMan);

	DeliveryMan update(DeliveryMan deliveryMan);

	void remove(DeliveryMan deliveryMan);

	DeliveryMan findOne(Long id);

	Iterable<DeliveryMan> findAll();
}