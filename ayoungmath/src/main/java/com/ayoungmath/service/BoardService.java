package com.ayoungmath.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayoungmath.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public List<HashMap<String, Object>> getClassList(String classSeq){
		return boardMapper.getClassList(classSeq);
	}
	
	public HashMap<String, Object> getVideoInfo(String classSeq){
		return boardMapper.getVideoInfo(classSeq);
	}
	
	public boolean saveVideoName(HashMap<String, Object> map) {
		return boardMapper.saveVideoName(map);
	};
	
	public HashMap<String, Object> getLogin(HashMap<String, Object> map){
		return boardMapper.getLogin(map);
	}
	
	public boolean saveUser(HashMap<String, Object> map) {
		return boardMapper.saveUser(map);
	};
	
	public String getUserName(String userId){
		return boardMapper.getUserName(userId);
	}
	
	public List<HashMap<String, Object>> getAllUser(){
		return boardMapper.getAllUser();
	}
	
	public boolean userUpdate(List<Integer> map) {
		return boardMapper.userUpdate(map);
	};
}
