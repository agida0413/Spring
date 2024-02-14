package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.GoodsService;
import com.sist.vo.FoodVO;
import com.sist.vo.GoodsVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsRestController {

	
	@Autowired
	private GoodsService service;
	
	
	
	@GetMapping(value="goods/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String gl(int page) throws JsonProcessingException {
		int rowisize=12;
		
		int start=(rowisize*page)-(rowisize-1);
		int end=rowisize*page;
		
		List<GoodsVO> list=service.goodslist(start, end);
		


		
		ObjectMapper mapper= new ObjectMapper();
		
		String json=mapper.writeValueAsString(list);
		
		
		
		return json;
		
	}
	
	@GetMapping(value="goods/page_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_page_vue(int page) throws JsonProcessingException {
	int totalpage=service.goodstotalpage();
	final int BLOCK=10;
	int startPage=((page-1)/BLOCK*BLOCK)+1;
	int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
	if(endPage>totalpage) {
		endPage=totalpage;
	}
	
	Map map= new HashMap();
	map.put("totalpage", totalpage);
	map.put("curapge", page);
	map.put("startpage", startPage);
	map.put("endpage", endPage);
	
	ObjectMapper mapper= new ObjectMapper();
	
	String json=mapper.writeValueAsString(map);
	
	return json;
	
	
	
		
	}
	
}
