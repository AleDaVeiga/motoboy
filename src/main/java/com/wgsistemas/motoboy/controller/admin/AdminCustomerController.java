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

import com.wgsistemas.motoboy.controller.admin.dominio.AdminCustomerReturn;
import com.wgsistemas.motoboy.controller.dominio.PageWrapper;
import com.wgsistemas.motoboy.model.Customer;
import com.wgsistemas.motoboy.service.CustomerService;
import com.wgsistemas.motoboy.service.StateService;
import com.wgsistemas.motoboy.validator.CustomerValidator;

@Controller
@RequestMapping(value = "/admin")
public class AdminCustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private StateService stateService;
    @Autowired
    private CustomerValidator customerValidator;
	
	@GetMapping(value = "/customer/")
	public String create(Model model) {
		model.addAttribute("customerForm", customerService.newCustomer());
		model.addAttribute("stateList", stateService.findAll());
		return "admin/customer/new";
	}
	
	@PostMapping(path = "/customer/")
	@Transactional
	public String create(@ModelAttribute("customerForm") Customer customerForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		customerValidator.validate(customerForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("messageError", "Não foi possível inserir o cliente.");
			return "admin/customer/new";
		} else {
			AdminCustomerReturn createReturn = customerService.createCustomer(customerForm, SecurityContextHolder.getContext().getAuthentication().getName());
			StringBuilder message = new StringBuilder();
			message.append("Cliente inserido com sucesso.");
			if (createReturn.getEmailStatus().isSuccess()) {
				message.append("\nE-mail enviado com sucesso.");
			} else if(createReturn.getEmailStatus().isError()) {
				redirectAttributes.addFlashAttribute("messageError", "Não foi possível enviar o e-mail para o cliente.\n" + createReturn.getEmailStatus().getErrorMessage());
			}
			redirectAttributes.addFlashAttribute("messageSuccess", message.toString());
			return "redirect:/admin/customer/" + createReturn.getCustomer().getId();
		}
	}
	
	@GetMapping(path = "/customer/{id}")
	public String update(@PathVariable Long id, Model model) {
		if (!model.containsAttribute("customerForm")) {
			Customer customer = customerService.findOne(id);
			model.addAttribute("customerForm", customer);
		}
		model.addAttribute("stateList", stateService.findAll());
		return "admin/customer/edit";
	}
	
	@PutMapping(path = "/customer/{id}")
	@Transactional
	public String update(@PathVariable Long id, @ModelAttribute("customerForm") Customer customerForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		customerValidator.validate(customerForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.customerForm", bindingResult);
            redirectAttributes.addFlashAttribute("customerForm", customerForm);
			redirectAttributes.addFlashAttribute("messageError", "Não foi possível atualizar o cliente.");
		} else {
			AdminCustomerReturn updateReturn = customerService.updateCustomer(customerForm);
			StringBuilder message = new StringBuilder();
			message.append("Cliente atualizado com sucesso.");
			if (updateReturn.getEmailStatus().isSuccess()) {
				message.append("\nE-mail enviado com sucesso.");
			} else if(updateReturn.getEmailStatus().isError()) {
				redirectAttributes.addFlashAttribute("messageError", "Não foi possível enviar o e-mail para o cliente.\n" + updateReturn.getEmailStatus().getErrorMessage());
			}
			redirectAttributes.addFlashAttribute("messageSuccess", message.toString());
		}
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
	public String findAll(@RequestParam(value = "search", required = false) String search, @PageableDefault(value = 10, page = 0, sort = { "fullName" }, direction = Direction.ASC) Pageable pageable, Model model) {
		PageWrapper<Customer> page = new PageWrapper<Customer>(customerService.findBySearchTerm(search, SecurityContextHolder.getContext().getAuthentication().getName(), pageable));
		model.addAttribute("page", page);
		return "admin/customer/list";
	}
}