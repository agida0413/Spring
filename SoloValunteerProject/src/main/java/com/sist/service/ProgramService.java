package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.OptionVO;
import com.sist.vo.ProgramVO;

public interface ProgramService {
	//옵션
	public List<OptionVO> stateOption();
	public List<OptionVO> cityOption(String state);
	public List<OptionVO> majorOption();
	public List<OptionVO> minorOption(String major_option);

	
	
	//프로그램
	public List<ProgramVO> programListData(Map map);
	
	public int programTotalPage(Map map);
	
	public ProgramVO programDetailData(int vno);
	

}
