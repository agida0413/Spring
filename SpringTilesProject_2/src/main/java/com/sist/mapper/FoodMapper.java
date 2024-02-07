package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;
public interface FoodMapper {
@Select("SELECT fno,poster,name,num "
      + "FROM(SELECT fno,poster,name,ROWNUM as num "
      + "FROM(SELECT fno,poster,name "
      + "FROM food_menu_house ORDER BY fno ASC)) "
      + "WHERE num BETWEEN #{start} AND #{end}")
public List<FoodVO> foodList(@Param ("start") int start,@Param("end") int end);
@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_menu_house")
public int foodTotalpage();


@Select("SELECT fno,score,poster,name,type,address,theme,price,time,seat,phone "
		+"FROM food_menu_house "
		+"WHERE fno=#{fno}")
public FoodVO foodDetail(int fno);

}