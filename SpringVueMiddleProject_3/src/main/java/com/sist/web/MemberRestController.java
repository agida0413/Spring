package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.service.FoodService;
import com.sist.vo.MemberVO;

@RestController
public class MemberRestController {

	@Autowired
	private FoodService service;
	
	@GetMapping(value="member/login_vue.do",produces = "text/plain;charset=UTF-8")
	public String login(String id,String pwd, HttpSession session) {
		
		String result="";
		
		MemberVO vo = service.isLogin(id, pwd);
		
		result=vo.getMsg();
		
		if(result.equals("OK")) {
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			
		}
		
		return result;
		
	}
	
	
}
