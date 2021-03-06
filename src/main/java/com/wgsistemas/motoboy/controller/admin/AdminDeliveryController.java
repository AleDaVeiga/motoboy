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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wgsistemas.motoboy.controller.admin.dominio.AdminDeliveryReturn;
import com.wgsistemas.motoboy.controller.dominio.PageWrapper;
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
	public String create(@RequestParam(value = "customerId", required = false) Long customerId, Model model) {
		model.addAttribute("deliveryForm", deliveryService.newDelivery(customerId));
		model.addAttribute("deliveryManList", deliveryManService.findAll(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute("customerList", customerService.findAll(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute("paymentMethodList", paymentMethodService.findAll());
		return "admin/delivery/new";
	}
	
	@PostMapping(path = "/delivery/")
	@Transactional
	public String create(@ModelAttribute("deliveryForm") Delivery deliveryForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		AdminDeliveryReturn createReturn = deliveryService.createDelivery(deliveryForm, SecurityContextHolder.getContext().getAuthentication().getName());
		
		StringBuilder message = new StringBuilder();
		message.append("Corrida inserida com sucesso.");
		if (createReturn.getEmailStatus().isSuccess()) {
			message.append("\nE-mail enviado com sucesso.");
		} else if(createReturn.getEmailStatus().isError()) {
			redirectAttributes.addFlashAttribute("messageError", "Não foi possível enviar o e-mail para o cliente.\n" + createReturn.getEmailStatus().getErrorMessage());
		}
		
		redirectAttributes.addFlashAttribute("messageSuccess", message.toString());
		return "redirect:/admin/delivery/";
	}
	
	@GetMapping(path = "/delivery/{id}")
	public String update(@PathVariable Long id, Model model) {
		Delivery delivery = deliveryService.findOne(id);		
		model.addAttribute("deliveryForm", delivery);
		model.addAttribute("deliveryManList", deliveryManService.findAll(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute("customerList", customerService.findAll(SecurityContextHolder.getContext().getAuthentication().getName()));	
		model.addAttribute("paymentMethodList", paymentMethodService.findAll());
		return "admin/delivery/edit";
	}
	
	@PutMapping(path = "/delivery/{id}")
	@Transactional
	public String update(@PathVariable Long id, @ModelAttribute("deliveryForm") Delivery deliveryForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		deliveryService.update(deliveryForm);
		redirectAttributes.addFlashAttribute("messageSuccess", "Corrida atualizada com sucesso.");
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
		PageWrapper<Delivery> page = new PageWrapper<Delivery>(deliveryService.findBySearchTerm(search, SecurityContextHolder.getContext().getAuthentication().getName(), pageable));
		model.addAttribute("page", page);	
		return "admin/delivery/list";
	}
}