package com.ayoungmath.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.Cookie;
import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ayoungmath.service.BoardService;
import com.ayoungmath.util.FileUtil;
import com.mysql.cj.protocol.x.Ok;

@RestController
public class MainController {
	
	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@Value("${file.upload.location}")
    private String resourcesLocation;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public ModelAndView pageMain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/login");
		Cookie[] cookies = request.getCookies();
		//ModelAndView mav = new ModelAndView("thymeleaf/main");
		// 특정 이름의 쿠키 값을 가져옵니다.
		String userId = "";
		for (Cookie cookie : cookies) {
		  String cookieName = cookie.getName();
		  if(cookieName.equals("userId")) {
			  userId = cookie.getValue();
		  };
		}
		
		if(userId.equals("")) {
			return mav;
		}else {
			return new ModelAndView("thymeleaf/list/list");
		}
		
	}
	
	@GetMapping("/main")
	public ModelAndView mainPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/main");
		return mav;
	}
	
	@GetMapping("/list")
	public ModelAndView listPage(HttpServletRequest request, @ModelAttribute("classSeq") String classSeq) {
		ModelAndView mav = new ModelAndView("thymeleaf/list/list");
		List<HashMap<String, Object>> map = boardService.getClassList(classSeq);
		mav.addObject("map", map);
		return mav;
	}
	
	@GetMapping("/view")
	public ModelAndView viewPage(HttpServletRequest request, @ModelAttribute("viewSeq") String VideoSeq) {
		ModelAndView mav = new ModelAndView("thymeleaf/view/view");
		HashMap<String, Object> map = boardService.getVideoInfo(VideoSeq);
		mav.addObject("map", map);
		
		// 파일 경로를 생성하여 모델에 추가합니다.
        String fileUrl = map.get("Video_Name")+"."+map.get("File_Ext");
        map.put("fileUrl", fileUrl);
        
		return mav;
	}
	
	@GetMapping("/videoSave")
	public ModelAndView savePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/save/videoSave");
        
		return mav;
	}
	
	@GetMapping("/video/{fileUrl}")
	public ResponseEntity<Resource> viewImageGoods(@PathVariable("fileUrl") String fileUrl){
		try {
			FileUtil fileUtil = new FileUtil(resourcesLocation);
			return fileUtil.fileVideoView(fileUrl);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e ) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/video/exec")
	public ResponseEntity<String> execVideo(HttpServletRequest request, 
			@RequestParam("grade") String grade,
			@RequestParam("videoName") String videoName,
			@RequestParam("fileExt") String fileExt){
		HashMap<String, Object> map = new HashMap<>();
		map.put("grade", grade);
		map.put("videoName", videoName);
		map.put("fileExt", fileExt);
		boardService.saveVideoName(map);
		return new ResponseEntity<>("ok",HttpStatus.OK);
	}
	
	@GetMapping("/ajax/login")
	public ResponseEntity<String> getLogin(HttpServletRequest request, 
			@RequestParam("userId") String userId,
			@RequestParam("password") String password){
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		
		return new ResponseEntity<>(userId,HttpStatus.OK);
	}
	
	
}
