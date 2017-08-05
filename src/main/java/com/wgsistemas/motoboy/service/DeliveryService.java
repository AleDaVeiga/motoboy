package com.wgsistemas.motoboy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wgsistemas.motoboy.model.Delivery;

public interface DeliveryService {
	Delivery newDelivery();
	
	Delivery create(Delivery delivery, String username);

	Delivery update(Delivery delivery, String username);

	void remove(Delivery delivery);

	void accept(Long id);

	Delivery findOne(Long id);

	@Deprecated
	Iterable<Delivery> findAll();

	Page<Delivery> findBySearchTerm(String search, Pageable pageable);

	Iterable<Delivery> findAllOrderByCustomer_FullNameAsc();

	Iterable<Delivery> findAllOrderByDeliveredBy_FullNameAsc();
	
	Iterable<Delivery> findByCustomerAccessOrderByDeliveryAtDesc(String username);
	
	Iterable<Delivery> findByCustomerAccessAndLastMounthDeliveryOrderByDeliveryAtDesc(String username);
}