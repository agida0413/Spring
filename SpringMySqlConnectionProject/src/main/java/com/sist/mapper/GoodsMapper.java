package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.GoodsVO;

public interface GoodsMapper {

@Select("SELECT no,goods_name,goods_price,goods_poster "
		+"FROM goods_all_data "
		+"ORDER BY NO ASC "
		+"LIMIT #{start},20")
public List<GoodsVO> goodsListData(int start);
//crud = > NVL = > isnull , TO_CHAR = > dateFormat()


@Select("SELECT CEIL(COUNT(*)/20.0) FROM GOODS_ALL_DATA")
public int goodsTotalPage();
}
