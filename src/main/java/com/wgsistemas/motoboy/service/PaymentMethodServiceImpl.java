package com.wgsistemas.motoboy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wgsistemas.motoboy.model.PaymentMethod;
import com.wgsistemas.motoboy.repository.PaymentMethodRepository;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	@Transactional
	public Iterable<PaymentMethod> findAll() {
		return paymentMethodRepository.findAll();
	}
}