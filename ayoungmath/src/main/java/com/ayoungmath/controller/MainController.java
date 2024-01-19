package com.ayoungmath.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
	@GetMapping("/")
	public ModelAndView indexPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/login");
		return mav;
	}
	
	@GetMapping("/test")
	public ModelAndView testPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/test");
		return mav;
	}
	
	@GetMapping("/main")
	public ModelAndView mainPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/main");
		return mav;
	}
	
	@GetMapping("/generic")
	public ModelAndView genericPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/generic");
		return mav;
	}
	
	@GetMapping("/elements")
	public ModelAndView elementsPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/elements");
		return mav;
	}
	
	@GetMapping("/default")
	public ModelAndView defaultPage(HttpServletRequest request) {
		System.out.println("default");
		ModelAndView mav = new ModelAndView("thymeleaf/common/default");
		return mav;
	}
}
