package com.wgsistemas.motoboy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wgsistemas.motoboy.dominio.PageWrapper;
import com.wgsistemas.motoboy.model.Delivery;
import com.wgsistemas.motoboy.service.CustomerService;
import com.wgsistemas.motoboy.service.DeliveryManService;
import com.wgsistemas.motoboy.service.DeliveryService;
import com.wgsistemas.motoboy.service.PaymentMethodService;

@Controller
@RequestMapping(value = "/admin")
public class AdminDeliveryController {
	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private DeliveryManService deliveryManService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	@GetMapping(value = "/delivery/")
	public String create(Model model) {
		model.addAttribute("deliveryForm", deliveryService.newDelivery());
		model.addAttribute("deliveryManList", deliveryManService.findAll());
		model.addAttribute("customerList", customerService.findAll());
		model.addAttribute("paymentMethodList", paymentMethodService.findAll());
		return "admin/delivery/new";
	}
	
	@PostMapping(path = "/delivery/")
	@Transactional
	public String create(@ModelAttribute("deliveryForm") Delivery deliveryForm, BindingResult bindingResult, Model model) {
		Delivery delivery = deliveryService.create(deliveryForm, SecurityContextHolder.getContext().getAuthentication().getName());		
		return "redirect:/admin/delivery/" + delivery.getId();
	}
	
	@GetMapping(path = "/delivery/{id}")
	public String update(@PathVariable Long id, Model model) {
		Delivery delivery = deliveryService.findOne(id);		
		model.addAttribute("deliveryForm", delivery);
		model.addAttribute("deliveryManList", deliveryManService.findAll());
		model.addAttribute("customerList", customerService.findAll());	
		model.addAttribute("paymentMethodList", paymentMethodService.findAll());
		return "admin/delivery/edit";
	}
	
	@PutMapping(path = "/delivery/{id}")
	@Transactional
	public String update(@PathVariable Integer id, @ModelAttribute("deliveryForm") Delivery deliveryForm, BindingResult bindingResult, Model model) {
		deliveryService.update(deliveryForm, SecurityContextHolder.getContext().getAuthentication().getName());			
		return "redirect:/admin/delivery/" + id;
	}
	
	@DeleteMapping(path = "/delivery/{id}")
	@Transactional
	public String remove(@PathVariable Long id) {
		Delivery delivery = deliveryService.findOne(id);		
		deliveryService.remove(delivery);		
		return "redirect:/admin/deliveries";
	}

	@GetMapping(path = "/deliveries")
	@Transactional(readOnly=true)
	public String findAll(@RequestParam(value = "search", required = false) String search, @PageableDefault(value = 10, page = 0, sort = { "deliveryAt" }, direction = Direction.DESC) Pageable pageable, Model model) {
		PageWrapper<Delivery> page = new PageWrapper<Delivery>(deliveryService.findBySearchTerm(search, pageable));
		model.addAttribute("page", page);	
		return "admin/delivery/list";
	}
}