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
}
