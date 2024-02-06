package com.sist.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.service.ProgramService;
import com.sist.vo.OptionVO;
import com.sist.vo.ProgramVO;

@Controller
public class ProgramController {

	@Autowired
	private ProgramService service;
	
	@RequestMapping("program/list.do")
	public String programList(OptionVO vo,Model model) {
		
		if(vo.getMajor_option()==null) {
			vo.setMajor_option("");
		}
		if(vo.getState()==null) {
			vo.setState("");
		}
		if(vo.getCity()==null) {
			vo.setCity("");
		}
		if(vo.getMinor_option()==null) {
			vo.setMinor_option("");
		}
		List<OptionVO> sList=service.stateOption();
		
		List<OptionVO> mjList=service.majorOption();
		
		
		model.addAttribute("ovo",vo);
		model.addAttribute("sList",sList);
		model.addAttribute("mjList",mjList);
		
		return "program";
	}
	
	
	@RequestMapping("program/detail.do")
	public String programDetail(int vno,Model model) {
		ProgramVO vo=service.programDetailData(vno);
		model.addAttribute("vo",vo);	
		return "program/detail";
	}
	
	
		
	
}
