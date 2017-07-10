package com.wgsistemas.motoboy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wgsistemas.motoboy.dominio.PageWrapper;
import com.wgsistemas.motoboy.model.DeliveryMan;
import com.wgsistemas.motoboy.service.DeliveryManService;

@Controller
@RequestMapping(value = "/admin")
public class AdminDeliveryManController {
	@Autowired
	private DeliveryManService deliveryManService;
	
	@RequestMapping(value = "/deliveryman/", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("deliveryManForm", new DeliveryMan());
		return "admin/deliveryman/new";
	}
	
	@RequestMapping(path = "/deliveryman/", method = RequestMethod.POST)
	@Transactional
	public String create(@ModelAttribute("deliveryManForm") DeliveryMan deliveryManForm, BindingResult bindingResult, Model model) {
		DeliveryMan deliveryMan = deliveryManService.create(deliveryManForm, SecurityContextHolder.getContext().getAuthentication().getName());		
		return "redirect:/admin/deliveryman/" + deliveryMan.getId();
	}
	
	@RequestMapping(path = "/deliveryman/{id}", method = RequestMethod.GET)
	public String update(@PathVariable Long id, Model model) {
		DeliveryMan deliveryMan = deliveryManService.findOne(id);		
		model.addAttribute("deliveryManForm", deliveryMan);		
		return "admin/deliveryman/edit";
	}
	
	@RequestMapping(path = "/deliveryman/{id}", method = RequestMethod.PUT)
	@Transactional
	public String update(@PathVariable Integer id, @ModelAttribute("deliveryManForm") DeliveryMan deliveryManForm, BindingResult bindingResult, Model model) {
		deliveryManService.update(deliveryManForm, SecurityContextHolder.getContext().getAuthentication().getName());			
		return "redirect:/admin/deliveryman/" + id;
	}
	
	@RequestMapping(path = "/deliveryman/{id}", method = RequestMethod.DELETE)
	@Transactional
	public String remove(@PathVariable Long id) {
		DeliveryMan deliveryMan = deliveryManService.findOne(id);		
		deliveryManService.remove(deliveryMan);		
		return "redirect:/admin/deliverymans";
	}

	@RequestMapping(path = "/deliverymans", method = RequestMethod.GET)
	@Transactional(readOnly=true)
	public String findAll(@PageableDefault(value = 10, page = 0) Pageable pageable, Model model) {
		PageWrapper<DeliveryMan> page = new PageWrapper<DeliveryMan>(deliveryManService.findAll(pageable));
		model.addAttribute("page", page);	
		return "admin/deliveryman/list";
	}
}