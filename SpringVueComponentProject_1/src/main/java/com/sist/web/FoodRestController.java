package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.FoodService;
import com.sist.vo.FoodVO;

@RestController
public class FoodRestController {

	@Autowired
	private FoodService service;
	
	@GetMapping(value="food/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list(int page) throws JsonProcessingException {
		int rowisize=12;
		
		int start=(rowisize*page)-(rowisize-1);
		int end=rowisize*page;
		
		List<FoodVO> list=service.foodListdata(start, end);
		
//		JSONArray arr= new JSONArray();
//		
//		
//		for(FoodVO vo:list) {
//			JSONObject obj=new JSONObject();
//			
//			obj.put("fno", vo.getFno());
//			obj.put("name", vo.getName());
//			obj.put("poster", vo.getPoster());
//			
//			arr.add(obj);
//			
//		}
//		
//		String json=arr.toJSONString();
		
		ObjectMapper mapper= new ObjectMapper();
		
		String json=mapper.writeValueAsString(list);
		
		
		
		return json;
		
	}
	
	@GetMapping(value="food/page_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_page_vue(int page) throws JsonProcessingException {
	int totalpage=service.totalpage();
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
