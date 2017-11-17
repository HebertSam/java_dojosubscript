package com.project.dojosubscriptions.controllers;
import com.project.dojosubscriptions.models.Package;

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

import com.project.dojosubscriptions.services.PackageService;
import com.project.dojosubscriptions.services.UserService;

@Controller
public class AdminController{
	//Member variables go here
	private PackageService packageService;
	private UserService userService;

	public AdminController(PackageService packageService, UserService userService){
		this.packageService = packageService;
		this.userService = userService;
	}
	
	@RequestMapping("/admin")
	public String index(@Valid @ModelAttribute("package") Package pack, Principal principal, Model model){
		model.addAttribute("packages", packageService.findAllPack());
		model.addAttribute("users", userService.getAllUsers());
		return "adminDash";
	}
	@PostMapping("/admin/createPackage")
	public String createPack(@Valid @ModelAttribute("package") Package pack, BindingResult result, RedirectAttributes flash){
		if(result.hasErrors()){
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/admin";
		}
		packageService.createPackage(pack);
		return "redirect:/admin";
	}
	@RequestMapping("/admin/deactivate/{id}")
	public String deactivate(@PathVariable("id") long id){
		Package pack = packageService.findOne(id);
		pack.setActive(false);
		packageService.updatePack(pack);
		return "redirect:/admin";
	}
	@RequestMapping("/admin/activate/{id}")
	public String activate(@PathVariable("id") long id){
		Package pack = packageService.findOne(id);
		pack.setActive(true);
		packageService.updatePack(pack);
		return "redirect:/admin";
	}
	@RequestMapping("/admin/delete/{id}")
	public String delete(@PathVariable("id") long id){
		packageService.delete(id);
		return "redirect:/admin";
	}		
}
