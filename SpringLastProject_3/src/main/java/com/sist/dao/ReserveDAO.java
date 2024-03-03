package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.ReserveMapper;
import com.sist.vo.FoodVO;

@Repository
public class ReserveDAO {

	
	@Autowired
	private ReserveMapper mapper;
	
	public List<FoodVO> foodreserveData(String type){
		return mapper.foodreserveData(type);
	}
}
