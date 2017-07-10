package com.wgsistemas.motoboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wgsistemas.motoboy.model.DeliveryMan;
import com.wgsistemas.motoboy.repository.DeliveryManRepository;

@Service
public class DeliveryManServiceImpl extends BaseServiceImpl<DeliveryMan, Long> implements DeliveryManService {
	@Autowired
	private DeliveryManRepository deliveryManRepository;

	@Override
	protected JpaRepository<DeliveryMan, Long> getRepository() {
		return deliveryManRepository;
	}

	@Transactional
	public void remove(DeliveryMan deliveryMan) {
		deliveryManRepository.delete(deliveryMan);
	}

	@Transactional(readOnly=true)
	public DeliveryMan findOne(Long id) {
		return deliveryManRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public Iterable<DeliveryMan> findAll() {
		return deliveryManRepository.findAll();
	}

	@Transactional(readOnly=true)
	public Page<DeliveryMan> findAll(Pageable pageable) {
		return deliveryManRepository.findAll(pageable);
	}
}