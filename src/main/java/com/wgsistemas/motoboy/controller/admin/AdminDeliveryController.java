package com.wgsistemas.motoboy.controller.admin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wgsistemas.motoboy.model.Delivery;
import com.wgsistemas.motoboy.service.CustomerService;
import com.wgsistemas.motoboy.service.DeliveryManService;
import com.wgsistemas.motoboy.service.DeliveryService;

@Controller
@RequestMapping(value = "/admin")
public class AdminDeliveryController {
	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private DeliveryManService deliveryManService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/delivery/", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("deliveryForm", new Delivery());
		model.addAttribute("deliveryManList", deliveryManService.findAll());
		model.addAttribute("customerList", customerService.findAll());
		return "admin/delivery/new";
	}
	
	@RequestMapping(path = "/delivery/", method = RequestMethod.POST)
	@Transactional
	public String create(@ModelAttribute("deliveryForm") Delivery deliveryForm, BindingResult bindingResult, Model model) {
		Delivery delivery = deliveryService.create(deliveryForm, SecurityContextHolder.getContext().getAuthentication().getName());		
		return "redirect:/admin/delivery/" + delivery.getId();
	}
	
	@RequestMapping(path = "/delivery/{id}", method = RequestMethod.GET)
	public String update(@PathVariable Long id, Model model) {
		Delivery delivery = deliveryService.findOne(id);		
		model.addAttribute("deliveryForm", delivery);
		model.addAttribute("deliveryManList", deliveryManService.findAll());
		model.addAttribute("customerList", customerService.findAll());	
		return "admin/delivery/edit";
	}
	
	@RequestMapping(path = "/delivery/{id}", method = RequestMethod.PUT)
	@Transactional
	public String update(@PathVariable Integer id, @ModelAttribute("deliveryForm") Delivery deliveryForm, BindingResult bindingResult, Model model) {
		deliveryService.update(deliveryForm, SecurityContextHolder.getContext().getAuthentication().getName());			
		return "redirect:/admin/delivery/" + id;
	}
	
	@RequestMapping(path = "/delivery/{id}", method = RequestMethod.DELETE)
	@Transactional
	public String remove(@PathVariable Long id) {
		Delivery delivery = deliveryService.findOne(id);		
		deliveryService.remove(delivery);		
		return "redirect:/admin/deliveries";
	}

	@RequestMapping(path = "/deliveries", method = RequestMethod.GET)
	@Transactional
	public String findAll(Model model) {
		Iterable<Delivery> deliveries = deliveryService.findAll();		
		model.addAttribute("deliveries", deliveries);		
		return "admin/delivery/list";
	}
}