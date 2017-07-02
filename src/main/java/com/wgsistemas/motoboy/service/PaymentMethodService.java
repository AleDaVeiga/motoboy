package com.wgsistemas.motoboy.service;

import com.wgsistemas.motoboy.model.PaymentMethod;

public interface PaymentMethodService {
	Iterable<PaymentMethod> findAll();
}