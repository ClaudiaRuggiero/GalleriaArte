package it.uniroma3.galleria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.galleria.model.Admin;
import it.uniroma3.galleria.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService admService;
	

	@GetMapping(value="/adminRegistration")
	public String getRegistrationPage(Model model) {
		Admin admin = new Admin();
		model.addAttribute(admin);
		return "adminRegistration";
	}
	
	@PostMapping(value="/adminRegistration")
	public String registrateAdmin(@Valid @ModelAttribute Admin admin, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "adminRegistration";
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(password);
		admService.addAdmin(admin);
		model.addAttribute(admin);
		return "index";
	}
	
}
