package com.wgsistemas.motoboy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wgsistemas.motoboy.model.DeliveryMan;
import com.wgsistemas.motoboy.repository.DeliveryManRepository;

@Service
public class DeliveryManServiceImpl implements DeliveryManService {
	@Autowired
	private DeliveryManRepository deliveryManRepository;

	@Transactional
	public DeliveryMan create(DeliveryMan deliveryMan) {
		return deliveryManRepository.save(deliveryMan);
	}

	@Transactional
	public DeliveryMan update(DeliveryMan deliveryMan) {
		return deliveryManRepository.save(deliveryMan);
	}

	@Transactional
	public void remove(DeliveryMan deliveryMan) {
		deliveryManRepository.delete(deliveryMan);
	}

	@Transactional
	public DeliveryMan findOne(Long id) {
		return deliveryManRepository.findOne(id);
	}

	@Transactional
	public Iterable<DeliveryMan> findAll() {
		return deliveryManRepository.findAll();
	}
}