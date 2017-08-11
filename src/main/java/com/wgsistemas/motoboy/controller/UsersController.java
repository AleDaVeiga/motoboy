package com.wgsistemas.motoboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.service.SecurityService;
import com.wgsistemas.motoboy.service.UserService;
import com.wgsistemas.motoboy.validator.UserValidator;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "/changepassword")
    public String changePassword(Model model) {
        model.addAttribute("userForm", new User());

        return "change_password";
    }

    @PostMapping(value = "/changepassword")
    public String changePassword(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "change_password";
        }

        userService.updatePassword(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/home";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
        	model.addAttribute("error", "Seu usuário e senha estão invalidos.");
        }
        if (logout != null) {
        	model.addAttribute("message", "Desconectado com sucesso.");
        }
        return "login";
    }
}