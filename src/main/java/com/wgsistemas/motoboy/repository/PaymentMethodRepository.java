package com.wgsistemas.motoboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wgsistemas.motoboy.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}