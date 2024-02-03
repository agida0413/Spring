package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FoodDAO;
import com.sist.dao.MemberDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.MemberVO;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodDAO dao;
	@Autowired
	private MemberDAO mDao;
	 

	@Override
	public List<FoodVO> foodListData(int start, int end) {
		// TODO Auto-generated method stub
		return dao.foodListData(start, end);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return dao.foodTotalPage();
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		// TODO Auto-generated method stub
		return dao.foodDetailData(fno);
	}

	@Override
	public MemberVO isLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		return mDao.isLogin(id, pwd);
	}

}
