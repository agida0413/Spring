package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.ProgramListMapper;
import com.sist.vo.OptionVO;
import com.sist.vo.ProgramVO;

@Repository
public class ProgramListDAO {

	@Autowired
	private ProgramListMapper mapper;
	
	public List<ProgramVO> programListData(Map map){
		return mapper.programListData(map);
	}
	
	public int programTotalPage(Map map) {
		return mapper.programTotalPage(map);
	}
	
	public ProgramVO programDetailData(int vno) {
		return mapper.programDetailData(vno);
	}
	
	
}
