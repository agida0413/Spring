package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.OptionVO;
import com.sist.vo.ProgramVO;

public interface ProgramListMapper {

	public List<ProgramVO> programListData(Map map);
	
}
