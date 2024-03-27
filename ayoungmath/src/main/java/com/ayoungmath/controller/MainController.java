package com.ayoungmath.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


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

		//ModelAndView mav = new ModelAndView("thymeleaf/main");
	
		return mav;
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
	
	@GetMapping("/save")
	public ModelAndView savePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/save/save");
        
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
	public ResponseEntity<String> execVideo(){
		return new ResponseEntity<>("ok",HttpStatus.OK);
	}
	
	@GetMapping("/ajax/login")
	public ResponseEntity<String> getLogin(){
		return new ResponseEntity<>("ok",HttpStatus.OK);
	}
	
	
}
