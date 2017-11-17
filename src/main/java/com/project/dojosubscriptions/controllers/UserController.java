package com.project.dojosubscriptions.controllers;

import com.project.dojosubscriptions.models.User;
import com.project.dojosubscriptions.services.UserService;
import com.project.dojosubscriptions.validators.UserValidator;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController{
	private UserService userService;
	private UserValidator userValidator;

	public UserController(UserService userService, UserValidator userValidator){
		this.userService = userService;
		this.userValidator = userValidator;
	}
	
	
	@RequestMapping("/register")
	public String registerForm(@Valid @ModelAttribute("user") User user){
		return "registration";
	}

	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, RedirectAttributes flash){
		userValidator.validate(user, result);
		if (result.hasErrors()){
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/register";
		}
		if(userService.getAllUsers().size() == 0){
			System.out.println("Thist is the first user");
			userService.saveUserWithAdminRole(user);
			return "redirect:/login";
		}

		userService.saveWithUserRole(user);

		return "redirect:/login";
	}

	@RequestMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, HttpSession session, Model model){
		if(error != null){
			model.addAttribute("errorMessage", "Invalid Credentails, Please try again");
		}
		if(logout != null){
			model.addAttribute("logoutMessage", "Logout Successfull");
		}
		return "login";
	}
}
