package com.wgsistemas.motoboy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wgsistemas.motoboy.model.Delivery;
import com.wgsistemas.motoboy.repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Transactional
	public Delivery create(Delivery delivery) {
		return deliveryRepository.save(delivery);
	}

	@Transactional
	public Delivery update(Delivery delivery) {
		return deliveryRepository.save(delivery);
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