package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;

public interface FoodMapepr {
@Select("SELECT fno,name,poster,num "
		+"FROM (SELECT fno,name,poster,ROWnum as num "
		+"FROM (SELECT fno,name,poster "
		+"FROM food_menu_house ORDER BY fno ASC)) "
		+"where num between #{start} and #{end}")
public List<FoodVO> foodListdata(@Param("start") int start, @Param("end") int end);

@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_menu_house")
public int totalpage();
}
