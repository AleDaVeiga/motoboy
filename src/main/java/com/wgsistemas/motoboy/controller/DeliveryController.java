package com.wgsistemas.motoboy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.wgsistemas.motoboy.service.DeliveryService;

@Controller
public class DeliveryController {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping(path = "/delivery/{id}/accept")
	public String accept(@PathVariable Long id, Model model) {
		deliveryService.accept(id);		
		return "redirect:/home";
	}

	@GetMapping(value = "/report/deliveries")
	public ModelAndView report(Model model) {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:report/deliveryByCustomer.jrxml");
		view.setApplicationContext(applicationContext);

		Map<String, Object> params = new HashMap<>();
		params.put("datasource", deliveryService.findByCustomerAccessOrderByDeliveryAtDesc(SecurityContextHolder.getContext().getAuthentication().getName()));
		params.put("format", "pdf");

		return new ModelAndView(view, params);
	}
}
