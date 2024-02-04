package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.OptionVO;
import com.sist.vo.ProgramVO;

public interface ProgramListMapper {
	//리스트
	public List<ProgramVO> programListData(Map map);
	//페이징
	public int programTotalPage(Map map);
	
	//상세보기
	@Select("SELECT vno,title,major_field,minor_field,TO_CHAR(collect_start,'YYYY.MM.DD') as dbCollect_start,TO_CHAR(collect_end,'YYYY.MM.DD') as dbCollect_end,"
			+"TO_CHAR(v_start,'YYYY.MM.DD') as dbV_start,TO_CHAR(v_end,'YYYY.MM.DD') as dbV_end,"
			+"runtime,rundate,int_runtime,collect_num,apply_num,volunteer_type,centername,si,gu,"
			+"address,target,active_type,hit,stack_apply,collect_state "
			+"FROM v_program "
			+"WHERE vno=#{vno}")
	public ProgramVO programDetailData(int vno);
	
	
	
	
		
	
	
}
