package com.wgsistemas.motoboy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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

	@Transactional
	public void remove(Delivery delivery) {
		deliveryRepository.delete(delivery);
	}

	@Transactional
	public Delivery findOne(Long id) {
		return deliveryRepository.findOne(id);
	}

	@Transactional
	public Iterable<Delivery> findAll() {
		return deliveryRepository.findAll();
	}

	@Transactional
	public Iterable<Delivery> findAllOrderByCustomer_FullNameAsc() {
		return deliveryRepository.findAll(orderByCustomer_FullNameAsc());
	}
	
	private Sort orderByCustomer_FullNameAsc() {
        return new Sort(Direction.ASC, "customer.fullName");
    }

	@Override
	public Iterable<Delivery> findAllOrderByDeliveredBy_FullNameAsc() {
		return deliveryRepository.findAll(orderByDeliveredBy_FullNameAsc());
	}
	
	private Sort orderByDeliveredBy_FullNameAsc() {
        return new Sort(Direction.ASC, "deliveredBy.fullName");
    }
}