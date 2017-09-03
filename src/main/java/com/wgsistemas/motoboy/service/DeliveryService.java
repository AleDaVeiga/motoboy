package com.wgsistemas.motoboy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wgsistemas.motoboy.controller.dominio.ReportDeliveryByCustomerForm;
import com.wgsistemas.motoboy.controller.dominio.ReportDeliveryByDeliveryManForm;
import com.wgsistemas.motoboy.controller.dominio.ReportDeliveryForm;
import com.wgsistemas.motoboy.model.Delivery;

public interface DeliveryService {
	Delivery newDelivery();
	
	Delivery create(Delivery delivery, String username);

	Delivery update(Delivery delivery);

	void remove(Delivery delivery);

	void accept(Long id);

	Delivery findOne(Long id);

	Iterable<Delivery> findAll(String username);

	Page<Delivery> findBySearchTerm(String search, String username, Pageable pageable);

	Iterable<Delivery> findByCustomerAccessOrderByDeliveryAtDesc(String username);
	
	Iterable<Delivery> findByCustomerAccessAndLastMounthDeliveryOrderByDeliveryAtDesc(String username);

	Iterable<Delivery> findByOwnerAndDeliveryAtOrderByDeliveryAtDesc(String name, ReportDeliveryForm reportDeliveryForm);
	
	Iterable<Delivery> findByOwnerAndDeliveryAtOrderByCustomer_FullNameAsc(String username, ReportDeliveryByCustomerForm reportDeliveryByCustomerForm);
	
	Iterable<Delivery> findByOwnerAndDeliveryAtOrderByDeliveredBy_FullNameAsc(String username, ReportDeliveryByDeliveryManForm reportDeliveryByDeliveryManForm);
}