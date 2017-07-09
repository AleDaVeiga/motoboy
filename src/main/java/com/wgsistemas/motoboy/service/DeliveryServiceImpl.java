package com.wgsistemas.motoboy.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wgsistemas.motoboy.model.Delivery;
import com.wgsistemas.motoboy.repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl extends BaseServiceImpl<Delivery, Long> implements DeliveryService {
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Override
	protected JpaRepository<Delivery, Long> getRepository() {
		return deliveryRepository;
	}
	
	@Override
	public Delivery newDelivery() {
		Delivery delivery = new Delivery();
		delivery.setDeliveryAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		return delivery;
	}

	@Transactional
	public void remove(Delivery delivery) {
		deliveryRepository.delete(delivery);
	}

	@Transactional(readOnly=true)
	public Delivery findOne(Long id) {
		return deliveryRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public Iterable<Delivery> findAll() {
		return deliveryRepository.findAll();
	}

	@Transactional(readOnly=true)
	public Page<Delivery> findAll(Pageable pageable) {
		return deliveryRepository.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public Iterable<Delivery> findAllOrderByCustomer_FullNameAsc() {
		return deliveryRepository.findAll(orderByCustomer_FullNameAsc());
	}
	
	private Sort orderByCustomer_FullNameAsc() {
        return new Sort(Direction.ASC, "customer.fullName");
    }

	@Transactional(readOnly=true)
	public Iterable<Delivery> findAllOrderByDeliveredBy_FullNameAsc() {
		return deliveryRepository.findAll(orderByDeliveredBy_FullNameAsc());
	}
	
	private Sort orderByDeliveredBy_FullNameAsc() {
        return new Sort(Direction.ASC, "deliveredBy.fullName");
    }
}