package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.GoodsMapper;
import com.sist.vo.GoodsVO;
@Repository
public class GoodsDAO {

	@Autowired
	private GoodsMapper mapper;
	public List<GoodsVO> goodslist(int start,int end){
	
		return mapper.goodslist(start, end);
	}
	
	public int goodstotalpage() {
		return mapper.goodstotalpage();
	}
}
