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
}
