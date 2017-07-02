package com.wgsistemas.motoboy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
}