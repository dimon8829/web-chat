package com.dihri.web.chat.controller;

import com.dihri.web.chat.form.UserForm;
import com.dihri.web.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Controller for working with users
 */
@Controller
public class UserController {

    private static final String TEMPLATE_USER ="user";

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String getTemplateLogin() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String getTemplateRegistration(Model model) {
        model.addAttribute("userForm", new UserForm());
        return TEMPLATE_USER;
    }

    @PostMapping(value = "/user")
    public String addUser(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return TEMPLATE_USER;
        }
        userService.createUser(userForm.getLogin(),userForm.getPassword());
        return "redirect:/login";
    }
}
