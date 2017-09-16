package com.wgsistemas.motoboy.controller.admin.dominio;

import com.wgsistemas.motoboy.mail.EmailStatus;
import com.wgsistemas.motoboy.model.Delivery;

public class AdminDeliveryReturn {
	private Delivery delivery;
	private EmailStatus emailStatus;

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public EmailStatus getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(EmailStatus emailStatus) {
		this.emailStatus = emailStatus;
	}
}