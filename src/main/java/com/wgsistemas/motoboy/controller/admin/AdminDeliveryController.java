package com.wgsistemas.motoboy.controller.admin;

import java.util.HashMap;
import java.util.Map;

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

import com.wgsistemas.motoboy.controller.dominio.PageWrapper;
import com.wgsistemas.motoboy.mail.EmailHtmlSender;
import com.wgsistemas.motoboy.mail.EmailStatus;
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
	@Autowired
	private EmailHtmlSender emailHtmlSender;
	
	@GetMapping(value = "/delivery/")
	public String create(Model model) {
		model.addAttribute("deliveryForm", deliveryService.newDelivery());
		model.addAttribute("deliveryManList", deliveryManService.findAll(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute("customerList", customerService.findAll(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute("paymentMethodList", paymentMethodService.findAll());
		return "admin/delivery/new";
	}
	
	@PostMapping(path = "/delivery/")
	@Transactional
	public String create(@ModelAttribute("deliveryForm") Delivery deliveryForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		deliveryService.create(deliveryForm, SecurityContextHolder.getContext().getAuthentication().getName());
		redirectAttributes.addFlashAttribute("messageSuccess", "Corrida inserida com sucesso.");
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
	
	//TODO: Teste de email
	@GetMapping(value = "/delivery/mail")
	public String createWithEmail(Model model) {
		model.addAttribute("deliveryForm", deliveryService.newDelivery());
		model.addAttribute("deliveryManList", deliveryManService.findAll(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute("customerList", customerService.findAll(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute("paymentMethodList", paymentMethodService.findAll());
		return "admin/delivery/mail";
	}
	
	@PostMapping(path = "/delivery/mail")
	@Transactional
	public String createWithEmail(@ModelAttribute("deliveryForm") Delivery deliveryForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		Delivery delivery = deliveryService.create(deliveryForm, SecurityContextHolder.getContext().getAuthentication().getName());
		
		Map<String, Object> context = new HashMap<>();
		context.put("title", "Solicitação de corrida");
		context.put("delivery", delivery);
		EmailStatus emailStatus = emailHtmlSender.send(delivery.getCustomer().getEmail(), "Solicitação de corrida", "delivery.ftl", context);
		
		redirectAttributes.addFlashAttribute("messageSuccess", "Corrida inserida com sucesso.\n" + emailStatus.getErrorMessage());
		return "redirect:/admin/delivery/mail";
	}
}