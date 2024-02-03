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
            @RequestBody OptionVO vo) throws JsonProcessingException {
		 		
			String ssType[]=ssConditionString.split("");
			String weekType[]=weekString.split("");
			String vType[]=vtConditionString.split("");
			
		        Map map= new HashMap();
		        map.put("vo",vo);
		        map.put("ssType", ssType);
		        map.put("weekType",weekType);
		        map.put("vType", vType);
		        map.put("start", 1);
		        map.put("end", 10);
		        List<ProgramVO> list= service.programListData(map);
		        
//		        System.out.println(list.size());
//		        System.out.println("시:"+vo.getState());
//		        System.out.println("군구:"+vo.getCity());
//		        System.out.println("큰분야:"+vo.getMajor_option());
//		        System.out.println("소분야:"+vo.getMinor_option());
//		        System.out.println("활동구분:"+vo.getActive_type());
//		        System.out.println("봉사대상:"+vo.getTarget_type());
//		        System.out.println("모집상태:"+vo.getCollect_state());
//		        System.out.println("봉사기간시작"+vo.getV_start());
//		        System.out.println("봉사기간끝"+vo.getV_end());
//		        System.out.println("모집기간시작일"+vo.getCollect_start());
//		        System.out.println("모집기간종료일"+vo.getCollect_end());
//		        System.out.println("검색어"+vo.getSs());
//		        System.out.println("검색조건"+ssConditionString);
//		        System.out.println("봉사자유형조건"+vtConditionString);
//		        System.out.println("요일조건"+weekString);
		       
		    
		        ObjectMapper mapper= new ObjectMapper();
		        String json=mapper.writeValueAsString(list);
		       
		    

		
		
		
		
		return json;
	}
}
