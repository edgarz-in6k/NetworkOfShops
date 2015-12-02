package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Controller
public class MainController {

	@RequestMapping("/")
	public String printWelcome(ModelMap model) {
		model.addAttribute("welcome", "Information of shops and shoppers");
		return "../../index";
	}

	@RequestMapping("/angular")
	public List<String> printWelcomeAngular() {
		return Arrays.asList("1","2","1","2");
	}
}