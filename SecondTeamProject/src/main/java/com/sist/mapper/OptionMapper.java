package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.OptionVO;

public interface OptionMapper {
	@Select("SELECT DISTINCT state FROM locoption")
	public List<OptionVO> stateOption();
	
	@Select("SELECT city FROM locoption "
			+"WHERE state=#{state}")
	public List<OptionVO> cityOption(String state);
	
	
	//ORDER BY CASE WHEN 컬럼명 = '특정값' THEN 0 ELSE 1 END, 컬럼명
	@Select("SELECT distinct major_option FROM (SELECT major_option FROM voption ORDER BY CASE WHEN major_option = '전체' THEN 0 ELSE 1 END, major_option)")
	public List<OptionVO> majorOption();
	
	@Select("SELECT minor_option FROM voption "
			+"WHERE major_option=#{major_option} "
			+"ORDER BY CASE WHEN major_option = '전체' THEN 0 ELSE 1 END, major_option")
	public List<OptionVO> minorOption(String major_option);
	
	
}
