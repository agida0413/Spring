package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 * 컴퓨터 
 * cpu/하드디스크...메모리 = > 컴포넌트
 * -----------------------
 * 조립:메인보드(스프링)
 * 
 */

import com.sist.mapper.FoodMapepr;
import com.sist.vo.FoodVO;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapepr mapper;
	
	public int totalpage() {
		return mapper.totalpage();
	}
	
	public List<FoodVO> foodListdata(int start,int end)
	{
		
		return mapper.foodListdata(start, end);
	}
}
