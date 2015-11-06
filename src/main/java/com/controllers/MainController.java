package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String printWelcome(ModelMap model) {
		model.addAttribute("welcome", "<h1>Information of shops and shoppers</h1>");
		return "../../index";
	}
}