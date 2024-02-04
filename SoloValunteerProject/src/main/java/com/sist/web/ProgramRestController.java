package com.sist.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.ProgramService;

import com.sist.vo.OptionVO;
import com.sist.vo.ProgramVO;

@RestController
public class ProgramRestController {
@Autowired
private ProgramService service;
	
	@GetMapping(value="program/stateChange_vue.do",produces = "text/plain;charset=UTF-8")
	public String stateChange_vue(OptionVO vo) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		String json="";
		List<OptionVO>list=new ArrayList<OptionVO>();
		if(!vo.getState().equals("전체")) {
			list=service.cityOption(vo.getState());
			json=mapper.writeValueAsString(list);
		}
		return json;
	}
	
	@GetMapping(value="program/major_fieldChange_vue.do",produces = "text/plain;charset=UTF-8")
	public String major_fieldChange_vue(OptionVO vo) throws JsonProcessingException {
		
		ObjectMapper mapper=new ObjectMapper();
		String json="";
		List<OptionVO>list=new ArrayList<OptionVO>();
		if(!vo.getMajor_option().equals("전체")) {
			list=service.minorOption(vo.getMajor_option());
			json=mapper.writeValueAsString(list);
			
		}
		return json;
	}
	
	
	@RequestMapping(value="program/find_vue.do",produces = "text/plain;charset=UTF-8")
	public String find_vue(@RequestParam("ssConditionString") String ssConditionString,
			@RequestParam("vtConditionString") String vtConditionString,
			@RequestParam("weekString") String weekString,
			int page,
            @RequestBody OptionVO vo) throws JsonProcessingException {
		 		
			String ssType[]=ssConditionString.split("");
			String weekType[]=weekString.split("");
			String vType[]=vtConditionString.split("");
			
			int rowSize=10;
			int start = (rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
		        Map map= new HashMap();
		        map.put("vo",vo);
		        map.put("ssType", ssType);
		        map.put("weekType",weekType);
		        map.put("vType", vType);
		        map.put("start", start);
		        map.put("end", end);
		        
		        List<ProgramVO> list= service.programListData(map);
		           
		        ObjectMapper mapper= new ObjectMapper();
		        String json=mapper.writeValueAsString(list);
		       
			
		return json;
	}
	
	
	
	
	@RequestMapping(value="program/listPage_vue.do",produces = "text/plain;charset=UTF-8")
	public String listPage_vue(@RequestParam("ssConditionString") String ssConditionString,
			@RequestParam("vtConditionString") String vtConditionString,
			@RequestParam("weekString") String weekString,
			int page,
            @RequestBody OptionVO vo) throws JsonProcessingException {
		 		
			String ssType[]=ssConditionString.split("");
			String weekType[]=weekString.split("");
			String vType[]=vtConditionString.split("");
			
		        Map map= new HashMap();
		        map.put("vo",vo);
		        map.put("ssType", ssType);
		        map.put("weekType",weekType);
		        map.put("vType", vType);
		       
		        int count=service.programTotalPage(map);
		        int totalPage= (int)Math.ceil(count/10.0);
		     
				
				
		        final int BLOCK=10;
		        int startPage=((page-1)/BLOCK*BLOCK)+1;
		        int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		        if (endPage>totalPage) {
		        	endPage=totalPage;
		        }
		        Map sendMap = new HashMap();
		     
		        
		        sendMap.put("totalpage",totalPage);
		        sendMap.put("start", startPage);
		        sendMap.put("end", endPage);
		        sendMap.put("curpage", page);
		        sendMap.put("startpage", startPage);
		        sendMap.put("endpage", endPage);
		        sendMap.put("size", count);
		        ObjectMapper mapper= new ObjectMapper();
		        String json= mapper.writeValueAsString(sendMap);
		        
		       
			
		return json;
	}
	
	
}
