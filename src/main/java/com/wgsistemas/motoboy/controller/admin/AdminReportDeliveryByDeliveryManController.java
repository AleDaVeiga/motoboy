package com.wgsistemas.motoboy.controller.admin;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.wgsistemas.motoboy.controller.dominio.ReportDeliveryByDeliveryManForm;
import com.wgsistemas.motoboy.service.DeliveryService;
import com.wgsistemas.motoboy.util.DateUtil;

@Controller
@RequestMapping(value = "/admin/report/deliveries")
public class AdminReportDeliveryByDeliveryManController {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping(value = "/deliverymans")
	public String report(Model model) {
		ReportDeliveryByDeliveryManForm reportDeliveryByDeliveryManForm = new ReportDeliveryByDeliveryManForm();
		ZonedDateTime today = DateUtil.newZonedDateTime();
		reportDeliveryByDeliveryManForm.setStartDeliveryAt(DateUtil.newDateFrom(today.withDayOfMonth(1)));
		reportDeliveryByDeliveryManForm.setEndDeliveryAt(DateUtil.newDateFrom(today.withDayOfMonth(1).plusMonths(1).minusDays(1)));
		model.addAttribute("reportDeliveryByDeliveryManForm", reportDeliveryByDeliveryManForm);
		return "admin/report/delivery/deliveriesByDeliveryMan";
	}
	
	@PostMapping(value = "/deliverymans")
	public ModelAndView filterReport(@ModelAttribute("reportDeliveryByDeliveryManForm") ReportDeliveryByDeliveryManForm reportDeliveryByDeliveryManForm, Model model) {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:report/admin/deliveryByDeliveryMan.jrxml");
		view.setApplicationContext(applicationContext);

		Map<String, Object> params = new HashMap<>();
		params.put("datasource", deliveryService.findByOwnerAndDeliveryAtOrderByDeliveredBy_FullNameAsc(SecurityContextHolder.getContext().getAuthentication().getName(), reportDeliveryByDeliveryManForm));
		params.put("format", "pdf");

		return new ModelAndView(view, params);
	}
}