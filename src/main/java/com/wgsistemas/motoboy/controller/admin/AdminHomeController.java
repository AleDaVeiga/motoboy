package com.wgsistemas.motoboy.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class AdminHomeController {
	@RequestMapping(value = { "/admin", "/admin/", "/admin/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "admin/home";
	}
}