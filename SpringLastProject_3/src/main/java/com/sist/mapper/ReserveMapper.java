package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;

public interface ReserveMapper {

	@Select("SELECT fno,poster,name "
			+"FROM food_menu_house "
			+"WHERE type LIKE '%'||#{type}||'%'")
	public List<FoodVO> foodreserveData(String type);
	
}
