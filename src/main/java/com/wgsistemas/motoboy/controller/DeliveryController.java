package com.wgsistemas.motoboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wgsistemas.motoboy.service.DeliveryService;

@Controller
public class DeliveryController {
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping(path = "/delivery/{id}/accept")
	public String update(@PathVariable Long id, Model model) {
		deliveryService.accept(id);		
		return "redirect:/home";
	}
}
