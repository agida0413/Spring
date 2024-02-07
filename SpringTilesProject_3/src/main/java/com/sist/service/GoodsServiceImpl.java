package com.sist.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class GoodsServiceImpl implements GoodsService{
   @Autowired
   private GoodsDAO dao;

	@Override
	public List<GoodsVO> goodsListData(Map map) {
		// TODO Auto-generated method stub
		return dao.goodsListData(map);
	}
	
	@Override
	public int goodsTotalPage(Map map) {
		// TODO Auto-generated method stub
		return dao.goodsTotalPage(map);
	}

	@Override
	public GoodsVO goodsDetailData(Map map) {
		// TODO Auto-generated method stub
		return dao.goodsDetailData(map);
	}
   
   
}