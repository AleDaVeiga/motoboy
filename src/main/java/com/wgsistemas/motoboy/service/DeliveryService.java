package com.wgsistemas.motoboy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wgsistemas.motoboy.controller.admin.dominio.AdminReportDeliveryByCustomerForm;
import com.wgsistemas.motoboy.controller.admin.dominio.AdminReportDeliveryByDeliveryManForm;
import com.wgsistemas.motoboy.controller.admin.dominio.AdminReportDeliveryForm;
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

	Iterable<Delivery> findByCustomerAccessOrderByDeliveryAtDesc(String username, ReportDeliveryForm reportDeliveryForm);
	
	Iterable<Delivery> findByCustomerAccessAndLastMounthDeliveryOrderByDeliveryAtDesc(String username);

	Iterable<Delivery> findByOwnerAndDeliveryAtAndStatusOrderByDeliveryAtDesc(String name, AdminReportDeliveryForm reportDeliveryForm);
	
	Iterable<Delivery> findByOwnerAndDeliveryAtAndStatusOrderByCustomer_FullNameAsc(String username, AdminReportDeliveryByCustomerForm reportDeliveryByCustomerForm);
	
	Iterable<Delivery> findByOwnerAndDeliveryAtAndStatusOrderByDeliveredBy_FullNameAsc(String username, AdminReportDeliveryByDeliveryManForm reportDeliveryByDeliveryManForm);
}