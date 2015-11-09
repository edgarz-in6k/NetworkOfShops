package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String printWelcome(ModelMap model) {
		model.addAttribute("welcome", "Information of shops and shoppers");
		return "../../index";
	}
}