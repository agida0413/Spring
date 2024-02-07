package com.sist.dao;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private MemberMapper mapper;
	
	
	
	
	public MemberVO isLogin(String id,String pwd) {
		
		MemberVO vo=new MemberVO();
		int count =mapper.idCount(id);
		
		if(count==0) {
		vo.setMsg("NOID");	
		}
		else {
			MemberVO dbVo=mapper.memberLogin(id);
			
			if(dbVo.getPwd().equals(pwd)) {
				vo.setMsg("OK");
				vo.setId(dbVo.getId());
				vo.setName(dbVo.getName());
				
	
				
			}
			else {
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
}
