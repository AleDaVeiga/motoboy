package com.wgsistemas.motoboy.controller.admin.dominio;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.wgsistemas.motoboy.util.DateUtil;

public class AdminReportDeliveryByDeliveryManForm {
	private Date startDeliveryAt;
	private Date endDeliveryAt;
	
	public static AdminReportDeliveryByDeliveryManForm ofActualMonth() {
		AdminReportDeliveryByDeliveryManForm reportDeliveryForm = new AdminReportDeliveryByDeliveryManForm();
		ZonedDateTime today = DateUtil.newZonedDateTime();
		reportDeliveryForm.setStartDeliveryAt(DateUtil.newDateFrom(today.withDayOfMonth(1)));
		reportDeliveryForm.setEndDeliveryAt(DateUtil.newDateFrom(today.withDayOfMonth(1).plusMonths(1).minusDays(1)));
		return reportDeliveryForm;
	}

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