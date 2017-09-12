package com.wgsistemas.motoboy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.service.UserService;
import com.wgsistemas.motoboy.validator.UserEmailValidator;

@Controller
@RequestMapping(value = "/admin")
public class AdminUserController {
	@Autowired
	private UserService userService;

    @Autowired
    private UserEmailValidator userValidator;
    
    @GetMapping(path = "/user/changeemail")
	public String update(Model model) {
		if (!model.containsAttribute("userForm")) {
			User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			model.addAttribute("userForm", user);
		}
		return "admin/user/edit";
	}
	
	@PutMapping(path = "/user/changeemail")
	@Transactional
	public String update(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		userForm.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		userValidator.validate(userForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm", bindingResult);
            redirectAttributes.addFlashAttribute("userForm", userForm);
			redirectAttributes.addFlashAttribute("messageError", "Não foi possível atualizar o usuário.");
		} else {
			userService.updateEmail(userForm);
			redirectAttributes.addFlashAttribute("messageSuccess", "Usuário atualizado com sucesso.");
		}
		return "redirect:/admin/user/changeemail";
	}
}