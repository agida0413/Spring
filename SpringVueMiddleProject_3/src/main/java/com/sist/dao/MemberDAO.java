package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private MemberMapper mapper;
	
	public MemberVO isLogin(String id,String pwd) {
		int count = mapper.idCount(id);
		String msg="";
		MemberVO vo= new MemberVO();
		if(count==0) {
			vo.setMsg("NOID");
		}
		else {
		
			MemberVO dbvo=	mapper.getinform(id);
			
			if(dbvo.getPwd().equals(pwd)) {
			vo.setMsg("OK");
			vo.setId(dbvo.getId());
			vo.setName(dbvo.getName());
			}
			else {
				vo.setMsg("NOPWD");	
			}
		}
		
		
		
	return vo;	
	}
}
