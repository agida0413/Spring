package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

@RestController
public class GoodsRestController {

	@Autowired
	private GoodsDAO gDao;
	
	@GetMapping(value = "goods/list_react.do",produces = "text/plain;charset=UTF-8")
	@CrossOrigin("http://localhost:3000")
	public String goods_list(int page) throws JsonProcessingException {
	
		int rowsize=page*20;
		int start=(rowsize*page)-rowsize;
		List<GoodsVO>list=gDao.goodsListData(start);
		
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
		
	}
	
	@GetMapping(value = "goods/page_react.do",produces = "text/plain;charset=UTF-8")
	@CrossOrigin("http://localhost:3000")
	public String goods_list_page(int page) throws JsonProcessingException {
		System.out.println(page);
		int totalpage=gDao.goodsAllTotalpage();
		
		Map map=new  HashMap();
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
		
	}
}
