package com.ayoungmath.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MainController {
	@GetMapping("/")
	public ModelAndView mainPage(HttpServletRequest request) {
		System.out.println("asdr");
		ModelAndView mav = new ModelAndView("thymeleaf/login");
		return mav;
	}
	
	@GetMapping("/test")
	public ModelAndView testPage(HttpServletRequest request) {
		System.out.println("test");
		ModelAndView mav = new ModelAndView("thymeleaf/test");
		return mav;
	}
}
