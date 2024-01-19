package com.ayoungmath.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HttpErrorController implements ErrorController{
	@RequestMapping(value = "/error")
	public ModelAndView handleError(HttpServletRequest request) { 
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE); 		
		ModelAndView modelAndView = new ModelAndView("");
		if(status != null){
			int statusCode = Integer.valueOf(status.toString()); 
			HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
			if(statusCode == HttpStatus.NOT_FOUND.value() ||statusCode == HttpStatus.BAD_REQUEST.value()){
				modelAndView.setViewName("thymeleaf/error/404");
			}
			
			if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
				modelAndView.setViewName("thymeleaf/error/405");
			}
			
			if(statusCode == HttpStatus.EXPECTATION_FAILED.value()){
				modelAndView.setViewName("thymeleaf/error/417");
			}
			
			if(statusCode == HttpStatus.FORBIDDEN.value()){ 
				modelAndView.setViewName("thymeleaf/error/500");
			}
			
			if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				modelAndView.setViewName("thymeleaf/error/500");
			}
			Object exceptionObj = request.getAttribute("javax.servlet.error.exception");
			if(exceptionObj!=null)
			{
				Throwable e = ((Exception) exceptionObj).getCause();
				if(e != null) {
					modelAndView.addObject("exception",e.getClass().getName());
					modelAndView.addObject("message",e.getMessage());
				}				
			}

			modelAndView.addObject("path", request.getAttribute("javax.servlet.error.request_uri"));
			modelAndView.addObject("code", status.toString());
			modelAndView.addObject("msg", httpStatus.getReasonPhrase());
			modelAndView.addObject("timestamp", new Date());
		}

		return modelAndView; 
	} 
}