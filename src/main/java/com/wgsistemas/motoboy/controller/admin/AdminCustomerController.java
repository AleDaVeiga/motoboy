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
import org.springframework.web.bind.annotation.RequestParam;

import com.wgsistemas.motoboy.model.Customer;
import com.wgsistemas.motoboy.service.CustomerService;

@Controller
@RequestMapping(value = "/admin")
public class AdminCustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/customer/", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("customerForm", new Customer());
		return "admin/customer/new";
	}
	
	@RequestMapping(path = "/customer/", method = RequestMethod.POST)
	@Transactional
	public String create(@ModelAttribute("customerForm") Customer customerForm, BindingResult bindingResult, Model model) {
		Customer customer = customerService.create(customerForm, SecurityContextHolder.getContext().getAuthentication().getName());		
		return "redirect:/admin/customer/" + customer.getId();
	}
	
	@RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
	public String update(@PathVariable Long id, Model model) {
		Customer customer = customerService.findOne(id);		
		model.addAttribute("customerForm", customer);		
		return "admin/customer/edit";
	}
	
	@RequestMapping(path = "/customer/{id}", method = RequestMethod.PUT)
	@Transactional
	public String update(@PathVariable Integer id, @ModelAttribute("customerForm") Customer customerForm, BindingResult bindingResult, Model model) {
		customerService.update(customerForm, SecurityContextHolder.getContext().getAuthentication().getName());			
		return "redirect:/admin/customer/" + id;
	}
	
	@RequestMapping(path = "/customer/{id}", method = RequestMethod.DELETE)
	@Transactional
	public String remove(@PathVariable Long id) {
		Customer customer = customerService.findOne(id);		
		customerService.remove(customer);		
		return "redirect:/admin/customers";
	}

	@RequestMapping(path = "/customers", method = RequestMethod.GET)
	@Transactional
	public String findAll(@RequestParam(value = "search", required = false) String search, Model model) {
		Iterable<Customer> customers = customerService.findAll(search);
		model.addAttribute("customers", customers);
		return "admin/customer/list";
	}
}