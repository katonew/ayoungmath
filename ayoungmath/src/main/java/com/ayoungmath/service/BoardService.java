package com.ayoungmath.service;

import java.util.ArrayList;
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

	public boolean deleteVideo(String videoSeq) {
		return boardMapper.deleteVideo(videoSeq);
	};

	public List<HashMap<String, Object>> getGrade(){
		return boardMapper.getGrade();
	};
	
	public List<HashMap<String, Object>> getSection(int gradeSeq){
		return boardMapper.getSection(gradeSeq);
	};
	
	public void updateSortOrder(HashMap<String, Object> map) {
		boardMapper.updateSortOrder(map);
	}
	
	public int getNextSortValue(int gradeSeq) {
		return boardMapper.getNextSortValue(gradeSeq);
	};
	
	public boolean saveSection(HashMap<String, Object> map) {
		return boardMapper.saveSection(map);
	};
	
	public int getMaxSectionValueByGrade() {
		return boardMapper.getMaxSectionValueByGrade();
	}

	public boolean deleteSection(String sectionSeq) {
		return boardMapper.deleteSection(sectionSeq);
	}
	
	public List<HashMap<String, Object>> getNavList() {
		List<HashMap<String, Object>> grades = boardMapper.getGrade();
		List<HashMap<String, Object>> result = new ArrayList<>();

        for (HashMap<String, Object> grade : grades) {
        	HashMap<String, Object> gradeMap = new HashMap<>();
            gradeMap.put("gradeName", grade.get("Grade_Name"));
            gradeMap.put("sections", boardMapper.getSection((Integer)grade.get("Grade_Seq"))); // 관계 매핑된 섹션 리스트
            result.add(gradeMap);
        }

        return result;
    }
}
