package com.wgsistemas.motoboy.service;

import com.wgsistemas.motoboy.model.Delivery;

public interface DeliveryService {
	Delivery create(Delivery delivery, String username);

	Delivery update(Delivery delivery, String username);

	void remove(Delivery delivery);

	Delivery findOne(Long id);

	Iterable<Delivery> findAll();

	Iterable<Delivery> findAllOrderByCustomer_FullNameAsc();

	Iterable<Delivery> findAllOrderByDeliveredBy_FullNameAsc();
}