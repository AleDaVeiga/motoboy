package com.wgsistemas.motoboy.controller.dominio;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReportDeliveryByDeliveryManForm {
	private Date startDeliveryAt;
	private Date endDeliveryAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getStartDeliveryAt() {
		return startDeliveryAt;
	}

	public void setStartDeliveryAt(Date startDeliveryAt) {
		this.startDeliveryAt = startDeliveryAt;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEndDeliveryAt() {
		return endDeliveryAt;
	}

	public void setEndDeliveryAt(Date endDeliveryAt) {
		this.endDeliveryAt = endDeliveryAt;
	}
}