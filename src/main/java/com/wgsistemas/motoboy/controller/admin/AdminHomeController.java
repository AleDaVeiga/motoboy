package com.wgsistemas.motoboy.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AdminHomeController {
	@GetMapping(value = { "/admin", "/admin/", "/admin/home" })
	public String home(Model model) {
		return "admin/home";
	}
}