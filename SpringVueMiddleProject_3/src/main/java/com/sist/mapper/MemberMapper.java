package com.sist.mapper;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.MemberVO;

public interface MemberMapper {

	
	@Select("SELECT COUNT(*) FROM vuemember WHERE id=#{id}")
	public int idCount(String id);
	@Select("SELECT id,name,pwd FROM vuemember WHERE id=#{id}")
	public MemberVO getinform(String id);
		
	
}
