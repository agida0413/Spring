package com.sist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.vo.FoodVO;


public interface FoodService {
	public int totalpage();
	public List<FoodVO> foodListdata(int start,int end);
	
}
