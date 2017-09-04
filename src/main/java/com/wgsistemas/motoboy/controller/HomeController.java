package com.wgsistemas.motoboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wgsistemas.motoboy.controller.dominio.ReportDeliveryForm;
import com.wgsistemas.motoboy.model.Delivery;
import com.wgsistemas.motoboy.service.DeliveryService;

@Controller
public class HomeController {
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping(value = { "/", "/home" })
	public String home(Model model) {
		Iterable<Delivery> deliveries = deliveryService.findByCustomerAccessAndLastMounthDeliveryOrderByDeliveryAtDesc(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("deliveries", deliveries);
		
		model.addAttribute("reportDeliveryForm", ReportDeliveryForm.ofActualMonth());
		return "home";
	}
}