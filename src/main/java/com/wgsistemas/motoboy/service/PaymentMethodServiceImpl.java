package com.wgsistemas.motoboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wgsistemas.motoboy.model.PaymentMethod;
import com.wgsistemas.motoboy.repository.PaymentMethodRepository;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	@Transactional(readOnly=true)
	public Iterable<PaymentMethod> findAll() {
		return paymentMethodRepository.findAll();
	}
}