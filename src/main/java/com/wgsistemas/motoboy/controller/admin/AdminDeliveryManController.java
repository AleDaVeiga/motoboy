package com.wgsistemas.motoboy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wgsistemas.motoboy.dominio.PageWrapper;
import com.wgsistemas.motoboy.model.DeliveryMan;
import com.wgsistemas.motoboy.service.DeliveryManService;

@Controller
@RequestMapping(value = "/admin")
public class AdminDeliveryManController {
	@Autowired
	private DeliveryManService deliveryManService;
	
	@GetMapping(value = "/deliveryman/")
	public String create(Model model) {
		model.addAttribute("deliveryManForm", new DeliveryMan());
		return "admin/deliveryman/new";
	}
	
	@PostMapping(path = "/deliveryman/")
	@Transactional
	public String create(@ModelAttribute("deliveryManForm") DeliveryMan deliveryManForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		DeliveryMan deliveryMan = deliveryManService.create(deliveryManForm, SecurityContextHolder.getContext().getAuthentication().getName());		
		redirectAttributes.addFlashAttribute("messageSuccess", "Motoboy inserido com sucesso.");
		return "redirect:/admin/deliveryman/" + deliveryMan.getId();
	}
	
	@GetMapping(path = "/deliveryman/{id}")
	public String update(@PathVariable Long id, Model model) {
		DeliveryMan deliveryMan = deliveryManService.findOne(id);		
		model.addAttribute("deliveryManForm", deliveryMan);		
		return "admin/deliveryman/edit";
	}
	
	@PutMapping(path = "/deliveryman/{id}")
	@Transactional
	public String update(@PathVariable Long id, @ModelAttribute("deliveryManForm") DeliveryMan deliveryManForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		deliveryManService.update(deliveryManForm, SecurityContextHolder.getContext().getAuthentication().getName());			
		redirectAttributes.addFlashAttribute("messageSuccess", "Motoboy atualizado com sucesso.");
		return "redirect:/admin/deliveryman/" + id;
	}
	
	@DeleteMapping(path = "/deliveryman/{id}")
	@Transactional
	public String remove(@PathVariable Long id) {
		DeliveryMan deliveryMan = deliveryManService.findOne(id);		
		deliveryManService.remove(deliveryMan);		
		return "redirect:/admin/deliverymans";
	}

	@GetMapping(path = "/deliverymans")
	@Transactional(readOnly=true)
	public String findAll(@PageableDefault(value = 10, page = 0) Pageable pageable, Model model) {
		PageWrapper<DeliveryMan> page = new PageWrapper<DeliveryMan>(deliveryManService.findAll(pageable));
		model.addAttribute("page", page);	
		return "admin/deliveryman/list";
	}
}