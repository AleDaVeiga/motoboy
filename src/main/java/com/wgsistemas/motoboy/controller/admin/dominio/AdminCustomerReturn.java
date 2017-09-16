package com.wgsistemas.motoboy.controller.admin.dominio;

import com.wgsistemas.motoboy.mail.EmailStatus;
import com.wgsistemas.motoboy.model.Customer;

public class AdminCustomerReturn {
	private Customer customer;
	private EmailStatus emailStatus;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public EmailStatus getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(EmailStatus emailStatus) {
		this.emailStatus = emailStatus;
	}
}