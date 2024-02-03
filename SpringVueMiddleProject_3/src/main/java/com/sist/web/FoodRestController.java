package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
	
	int rowSize=12;
	int start=(rowSize*page)-(rowSize-1);
	int end=(rowSize*page);
	List<FoodVO> list =service.foodListData(start, end);
	
	ObjectMapper mapper=new ObjectMapper();
	String json= mapper.writeValueAsString(list);
	return json;
}

@GetMapping(value="food/page_vue.do",produces = "text/plain;charset=UTF-8")
public String food_page(int page) throws JsonProcessingException {
	int totalpage=service.foodTotalPage();
	final int Block=10;
	
	int startPage=((page-1)/Block*Block)+1;
	int endPage=((page-1)/Block*Block)+Block;
	if(endPage>totalpage) {
		endPage=totalpage;
	}
	Map map =new HashMap();
	map.put("curpage", page);
	map.put("startpage", startPage);
	map.put("endpage", endPage);
	map.put("totalpage", totalpage);
		
	ObjectMapper mapper =new ObjectMapper();
	String json= mapper.writeValueAsString(map);
	
	return json;

}

@GetMapping(value="food/detail_vue.do",produces = "text/plain;charset=UTF-8")
public String food_detail(int fno,HttpSession session) throws JsonProcessingException {
	FoodVO vo = service.foodDetailData(fno);
	String id= (String)session.getAttribute("id");
	
	if(id==null) {
		vo.setSession("");
			
	}
	else {
		vo.setSession(id);
	}
	
	ObjectMapper mapper= new ObjectMapper();
	String json= mapper.writeValueAsString(vo);
	
	return json;
}
}
