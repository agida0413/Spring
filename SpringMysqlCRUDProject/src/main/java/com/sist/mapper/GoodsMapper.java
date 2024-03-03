package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.GoodsVO;

public interface GoodsMapper {
@Select("SELECT no,goods_price,goods_name,goods_poster "
		+"FROM goods_all_data "
		+"ORDER BY no ASC "
		+"LIMIT #{start},20")
public List<GoodsVO>goodsListData(int start);

@Select("SELECT CEIL(COUNT(*)/20.0) FROM goods_all_data")
public int goodsAllTotalpage();
}
