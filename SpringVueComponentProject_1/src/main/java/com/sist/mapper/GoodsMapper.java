package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;
import com.sist.vo.GoodsVO;

public interface GoodsMapper {

	
	@Select("SELECT no,goods_name,goods_poster,num "
			+"FROM (SELECT no,goods_name,goods_poster,ROWnum as num "
			+"FROM (SELECT no,goods_name,goods_poster "
			+"FROM goods_all ORDER BY no ASC)) "
			+"where num between #{start} and #{end}")
	public List<GoodsVO> goodslist(@Param("start") int start, @Param("end") int end);

	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodstotalpage();
}
