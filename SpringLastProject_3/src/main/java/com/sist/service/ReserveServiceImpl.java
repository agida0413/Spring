package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.ReserveDAO;
import com.sist.vo.FoodVO;
@Service
public class ReserveServiceImpl implements ReserveService{

	@Autowired
	private ReserveDAO dao;
	@Override
	public List<FoodVO> foodreserveData(String type) {
		// TODO Auto-generated method stub
		return dao.foodreserveData(type);
	}

}
