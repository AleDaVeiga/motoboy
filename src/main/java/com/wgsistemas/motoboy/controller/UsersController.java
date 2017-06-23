package com.wgsistemas.motoboy.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.service.AutoLogin;
import com.wgsistemas.motoboy.service.UserService;
import com.wgsistemas.motoboy.validator.UserValidator;

@Controller
public class UsersController {
	@Autowired
	private UserValidator userValidate;
			
	@Autowired
	private UserService userService;

	@Autowired
	private AutoLogin autoLogin;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@Transactional
	public String createUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		userValidate.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			return "register";
		}

		userService.save(user);
		autoLogin.autoLogin(user.getUsername(), user.getPassword());
		return "redirect:/home";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = { "/", "/home" })
	public String home(Model model) {
		return "home";
	}
}