package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.OptionMapper;
import com.sist.vo.OptionVO;
@Repository
public class OptionDAO {
	@Autowired
	private OptionMapper mapper;
	//시도 옵션
		public List<OptionVO> stateOption(){
			return mapper.stateOption();
		}
		
		//구군 옵션
		public List<OptionVO> cityOption(String state){
			
			return mapper.cityOption(state);
		}
		
		public List<OptionVO> majorOption(){
			return mapper.majorOption();
		}
		
		public List<OptionVO> minorOption(String major_option){
			return mapper.minorOption(major_option);
		}
		
		
}
