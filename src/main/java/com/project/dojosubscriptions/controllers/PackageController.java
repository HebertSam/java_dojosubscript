package com.project.dojosubscriptions.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.dojosubscriptions.models.User;
import com.project.dojosubscriptions.models.Package;
import com.project.dojosubscriptions.services.PackageService;
import com.project.dojosubscriptions.services.UserService;

@Controller
public class PackageController{
	private UserService userService;
	private PackageService packageService;

	public PackageController(UserService userService, PackageService packageService){
		this.userService = userService;
		this.packageService = packageService;
	}
	
	@RequestMapping("/dashboard")
	public String index(Principal principal, Model model){
		String username = principal.getName();
		User currentUser = userService.findByUsername(username);
		for (int i = 0; i < currentUser.getRoles().size(); i++){
			if (currentUser.getRoles().get(i).getName().equals("ROLE_ADMIN")){
				return "redirect:/admin";
			}
		}
		if(currentUser.getPack() != null){
			return "redirect:/package";
		}
		// List<Package> packages = packageService.findAllPack();
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("packages", packageService.findAllPack());
		return "selection";
	}
	@PostMapping("/choosePackage")
	public String choosePack(@RequestParam("dueDay") int dueDay, @RequestParam("pack") long packId, RedirectAttributes flash, Principal principal){
		if (dueDay > 31){
			flash.addFlashAttribute("error", "your due date must be between 1 - 31");
			return "rediredct:/dashboard";
		}
		String username = principal.getName();
		User currentUser = userService.findByUsername(username);
		Package pack = packageService.findOne(packId);
		currentUser.setPack(pack);
		currentUser.setDueDate(dueDay);
		userService.updateUser(currentUser);
		return "redirect:/package";
	}
	@RequestMapping("/package")
	public String yourPackage(Principal principal, Model model){
		String username = principal.getName();
		User currentUser = userService.findByUsername(username);
		model.addAttribute("user", currentUser);
		return "index";
	}	
}
