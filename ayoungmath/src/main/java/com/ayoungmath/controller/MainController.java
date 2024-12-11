package com.ayoungmath.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.Cookie;
import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ayoungmath.service.BoardService;
import com.ayoungmath.util.FileUtil;

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
		// 특정 이름의 쿠키 값을 가져옵니다.
		String userId = "";
		if (cookies != null && cookies.length > 0) {
		    for (Cookie cookie : cookies) {
		        String cookieName = cookie.getName();
		        if (cookieName.equals("userId")) {
		            userId = cookie.getValue();
		            break; // Assuming "userId" cookie is unique, no need to continue looping
		        }
		    }
		}
		
		if(userId.equals("")) {
			return mav;
		}else {
			return new ModelAndView("thymeleaf/main");
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
		Cookie[] cookies = request.getCookies();
		String userId = "";
		if (cookies != null && cookies.length > 0) {
		    for (Cookie cookie : cookies) {
		        String cookieName = cookie.getName();
		        if (cookieName.equals("userId")) {
		            userId = cookie.getValue();
		            break; // Assuming "userId" cookie is unique, no need to continue looping
		        }
		    }
		}
		mav.addObject("userId",userId);
		mav.addObject("map", map);
		return mav;
	}
	
	@GetMapping("/view")
	public ModelAndView viewPage(HttpServletRequest request, 
			@ModelAttribute("viewSeq") String VideoSeq) {
		ModelAndView mav = new ModelAndView("thymeleaf/view/view");
		HashMap<String, Object> map = boardService.getVideoInfo(VideoSeq);
		Cookie[] cookies = request.getCookies();
		String userId = "";
		if (cookies != null && cookies.length > 0) {
		    for (Cookie cookie : cookies) {
		        String cookieName = cookie.getName();
		        if (cookieName.equals("userId")) {
		            userId = cookie.getValue();
		            break; // Assuming "userId" cookie is unique, no need to continue looping
		        }
		    }
		}
		mav.addObject("map", map);
		mav.addObject("VideoSeq",VideoSeq);
		mav.addObject("userId",userId);

		
		// 파일 경로를 생성하여 모델에 추가합니다.
        String fileUrl = map.get("Video_Name")+"."+map.get("File_Ext");
        map.put("fileUrl", fileUrl);
        
		return mav;
	}
	
	@GetMapping("/videoSave")
	public ModelAndView videoSavePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/save/videoSave");
		List<HashMap<String, Object>> gradeList = boardService.getGrade();
		mav.addObject("gradeList", gradeList);
        
		return mav;
	}

	@GetMapping("/sectionList")
	public List<HashMap<String, Object>> sectionList(HttpServletRequest request,
	@RequestParam("gradeSeq") int gradeSeq) {
		List<HashMap<String, Object>> sectionList = boardService.getSection(gradeSeq);
        return sectionList;
	}
	
	@GetMapping("/userSave")
	public ModelAndView userSavePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/save/userSave");
        
		return mav;
	}
	
	@GetMapping("/userList")
	public ModelAndView userListPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("thymeleaf/list/userList");
		List<HashMap<String, Object>> map = boardService.getAllUser();
		mav.addObject("map", map);
		return mav;
	}
	
	@GetMapping("/getMainLogo")
	public ResponseEntity<Resource> viewImage(){		
		try {
			FileUtil fileUtil = new FileUtil(resourcesLocation);
			return fileUtil.fileImageView("ayoungmathimage.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e ) {
			e.printStackTrace();
			return null;
		}
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
			@RequestParam("title") String title,
			@RequestParam("section") String section,
			MultipartFile videoFile) throws Exception{
		FileUtil fileUtil = new FileUtil(resourcesLocation);
		String uuid= UUID.randomUUID().toString();
		String ext = StringUtils.getFilenameExtension(videoFile.getOriginalFilename().toLowerCase());
		try {
			fileUtil.fileUpload(uuid, videoFile,"video");
		} catch (Exception e) {
			return new ResponseEntity<>("동영상 저장에 실패하였습니다.",HttpStatus.BAD_REQUEST);			
		} 
		HashMap<String, Object> map = new HashMap<>();
		map.put("grade", grade);
		map.put("section", section);
		map.put("title", title);
		map.put("videoName", uuid);
		map.put("fileExt", ext);
		int sort = boardService.getNextSortValue(Integer.valueOf(section));
		map.put("sort", sort);
		try {
			boardService.saveVideoName(map);
			return new ResponseEntity<>("ok",HttpStatus.OK);
		}catch (Exception e) {
			fileUtil.fileDelete(uuid, "video");
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}

	
	@PostMapping("/video/delete")
	public ResponseEntity<String> deleteVideo(HttpServletRequest request, 
			@RequestParam("videoSeq") String videoSeq){
		FileUtil fileUtil = new FileUtil(resourcesLocation);
		HashMap<String, Object> videoMap = boardService.getVideoInfo(videoSeq);
		String videoName = (String)videoMap.get("Video_Name");
		String fileExt = (String)videoMap.get("File_Ext");
		Integer grade = (Integer)videoMap.get("Grade");
		String fileName = videoName+"."+fileExt;

		System.out.println("FileName : " + fileName);
		try{
			fileUtil.fileDelete(fileName,"video");
			boardService.deleteVideo(videoSeq);
		}catch (Exception e){
			return new ResponseEntity<>("동영상 삭제에 실패하였습니다.",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(String.valueOf(grade),HttpStatus.OK);

	}
	
	@PostMapping("/user/exec")
	public ResponseEntity<String> execUser(HttpServletRequest request, 
			@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			@RequestParam("userName") String userName){
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		HashMap<String, Object> resultMap = boardService.getLogin(map);
		if (resultMap != null && (!resultMap.isEmpty() || resultMap.size() > 0)) {
		    return new ResponseEntity<>("이미 있는 회원입니다.", HttpStatus.BAD_REQUEST);
		}
		map.put("userName", userName);
		boardService.saveUser(map);
		return new ResponseEntity<>("ok",HttpStatus.OK);
	}
	
	@GetMapping("/ajax/login")
	public ResponseEntity<String> getLogin(HttpServletRequest request, 
			@RequestParam("userId") String userId,
			@RequestParam("password") String password){
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		HashMap<String, Object> resultMap = boardService.getLogin(map);
		if(resultMap==null || resultMap.isEmpty()) {
			return new ResponseEntity<>("잘못된 로그인 정보입니다.",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userId,HttpStatus.OK);
	}
	
	@GetMapping("/user/name")
	public ResponseEntity<String> getUserName(HttpServletRequest request, 
			@RequestParam("userId") String userId){
		String userName = boardService.getUserName(userId);
		
		return new ResponseEntity<>(userName,HttpStatus.OK);
	}
	
	@PutMapping("/user/update")
	public ResponseEntity<String> userUpdate(HttpServletRequest request, 
			@RequestBody List<Integer> userList){
		boardService.userUpdate(userList);
		return new ResponseEntity<>("OK",HttpStatus.OK);
	}
	
	@PostMapping("/saveOrder")
	@ResponseBody
	public ResponseEntity<String> saveOrder(@RequestBody List<Integer> order) {
	    try {
	        for (int i = 0; i < order.size(); i++) {
	        	HashMap<String, Object> map = new HashMap<>();
	            int videoSeq = order.get(i);
	            int sort = i + 1; // 새 순서
	            map.put("videoSeq",videoSeq);
	            map.put("sort",sort);
	            boardService.updateSortOrder(map);
	        }
	        return new ResponseEntity<>("OK",HttpStatus.OK);
	    } catch (Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	    }
	}
	
}
