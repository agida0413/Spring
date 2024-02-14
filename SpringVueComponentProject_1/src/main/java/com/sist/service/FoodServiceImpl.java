package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodDAO fDao;

	@Override
	public int totalpage() {
		// TODO Auto-generated method stub
		return fDao.totalpage();
	}

	@Override
	public List<FoodVO> foodListdata(int start, int end) {
		// TODO Auto-generated method stub
		return fDao.foodListdata(start, end);
	}
}
