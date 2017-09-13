package com.wgsistemas.motoboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wgsistemas.motoboy.mail.EmailStatus;
import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.service.SecurityService;
import com.wgsistemas.motoboy.service.UserService;
import com.wgsistemas.motoboy.validator.UserPasswordValidator;
import com.wgsistemas.motoboy.validator.UserRecoverValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserPasswordValidator userPasswordValidator;
    
    @Autowired
    private UserRecoverValidator userRecoverValidator;

    @GetMapping(value = "/changepassword")
    public String changePassword(Model model) {
        model.addAttribute("userForm", new User());

        return "change_password";
    }

    @PostMapping(value = "/changepassword")
    public String changePassword(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userPasswordValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "change_password";
        }

        userService.updatePassword(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/home";
    }

    @GetMapping(value = "/recoveruser")
    public String recoveruser(Model model) {
        model.addAttribute("userForm", new User());

        return "recover_user";
    }

	@PostMapping(value = "/recoveruser")
	public String recoveruser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		userRecoverValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "recover_user";
		}
		EmailStatus emailStatus = userService.recoverUser(userForm);
		if (emailStatus.isSuccess()) {
			redirectAttributes.addFlashAttribute("messageSuccess", "E-mail de recuperação de senha enviado com sucesso.");
		} else {
			redirectAttributes.addFlashAttribute("messageError", "Não foi possível enviar o e-mail de recuperação de senha.\n" + emailStatus.getErrorMessage());
		}
		return "redirect:/recoveruser";
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