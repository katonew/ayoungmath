package com.ayoungmath.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
	
	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	
	@GetMapping("/")
	public ModelAndView pageMain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/login");
	
		return mav;
	}
	
	@GetMapping("/main")
	public ModelAndView mainPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/main");
		return mav;
	}
	
	@GetMapping("/list")
	public ModelAndView listPage(HttpServletRequest request, @ModelAttribute("classSeq") String classSeq) {
		System.out.println("classSeq :: ");
		System.out.println(classSeq);
		ModelAndView mav = new ModelAndView("thymeleaf/list/listProduct");
		return mav;
	}
}
