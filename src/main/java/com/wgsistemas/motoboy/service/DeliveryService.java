package com.wgsistemas.motoboy.service;

import com.wgsistemas.motoboy.model.Delivery;

public interface DeliveryService {
	Delivery create(Delivery delivery);

	Delivery update(Delivery delivery);

	void remove(Delivery delivery);

	Delivery findOne(Long id);

	Iterable<Delivery> findAll();
}