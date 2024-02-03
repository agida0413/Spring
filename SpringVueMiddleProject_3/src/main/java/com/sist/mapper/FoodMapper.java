package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;
/*
 * 
 *    private int fno;
   private double score; 
   private String poster,name,address,phone,price,time,type,theme,seat;
 */
public interface FoodMapper {

	@Select("SELECT fno,poster,name,num "
			+"FROM (SELECT fno,poster,name,rownum as num "
			+"FROM (SELECT fno,poster,name "
			+"FROM food_menu_house ORDER BY fno ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start,@Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_menu_house ")
	public int foodTotalPage();
	
	@Select("SELECT fno,score,poster,name,type,address,phone,theme,price,time,seat "
			+"FROM food_menu_house "
			+"WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
