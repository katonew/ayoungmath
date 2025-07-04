package com.ayoungmath.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {
	List<HashMap<String, Object>> getClassList(String classSeq);
	HashMap<String, Object> getVideoInfo(String VideoSeq);
	boolean saveVideoName(HashMap<String, Object> map);
	HashMap<String, Object> getLogin(HashMap<String, Object> map);
	boolean saveUser(HashMap<String, Object> map);
	String getUserName(String userId);
	List<HashMap<String, Object>> getAllUser();
	boolean userUpdate(List<Integer> map);
	boolean deleteVideo(String VideoSeq);	
	List<HashMap<String, Object>> getGrade();
	List<HashMap<String, Object>> getSection(int gradeSeq);
	void updateSortOrder(HashMap<String, Object> map);
	int getNextSortValue(int gradeSeq);
	boolean saveSection(HashMap<String, Object> map);
	int getMaxSectionValueByGrade();
	boolean deleteSection(String sectionSeq);
}
