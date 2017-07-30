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
import org.springframework.web.bind.annotation.RequestParam;

import com.wgsistemas.motoboy.dominio.PageWrapper;
import com.wgsistemas.motoboy.model.Customer;
import com.wgsistemas.motoboy.service.CustomerService;
import com.wgsistemas.motoboy.service.StateService;

@Controller
@RequestMapping(value = "/admin")
public class AdminCustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private StateService stateService;
	
	@GetMapping(value = "/customer/")
	public String create(Model model) {
		model.addAttribute("customerForm", customerService.newCustomer());
		model.addAttribute("stateList", stateService.findAll());
		return "admin/customer/new";
	}
	
	@PostMapping(path = "/customer/")
	@Transactional
	public String create(@ModelAttribute("customerForm") Customer customerForm, BindingResult bindingResult, Model model) {
		Customer customer = customerService.create(customerForm, SecurityContextHolder.getContext().getAuthentication().getName());		
		return "redirect:/admin/customer/" + customer.getId();
	}
	
	@GetMapping(path = "/customer/{id}")
	public String update(@PathVariable Long id, Model model) {
		Customer customer = customerService.findOne(id);		
		model.addAttribute("customerForm", customer);	
		model.addAttribute("stateList", stateService.findAll());	
		return "admin/customer/edit";
	}
	
	@PutMapping(path = "/customer/{id}")
	@Transactional
	public String update(@PathVariable Integer id, @ModelAttribute("customerForm") Customer customerForm, BindingResult bindingResult, Model model) {
		customerService.update(customerForm, SecurityContextHolder.getContext().getAuthentication().getName());			
		return "redirect:/admin/customer/" + id;
	}
	
	@DeleteMapping(path = "/customer/{id}")
	@Transactional
	public String remove(@PathVariable Long id) {
		Customer customer = customerService.findOne(id);		
		customerService.remove(customer);		
		return "redirect:/admin/customers";
	}

	@GetMapping(path = "/customers")
	@Transactional(readOnly = true)
	public String findAll(@RequestParam(value = "search", required = false) String search, @PageableDefault(value = 10, page = 0) Pageable pageable, Model model) {
		PageWrapper<Customer> page = new PageWrapper<Customer>(customerService.findBySearchTerm(search, pageable));
		model.addAttribute("page", page);
		return "admin/customer/list";
	}
}