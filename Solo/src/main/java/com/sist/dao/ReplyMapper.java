package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ReplyMapper {

	
	@Select("SELECT * FROM YJREPLY")
	public List<ReplyVO> replyList();
	
	@Select("SELECT * FROM YJREPLY WHERE rno=#{rno}")
	public ReplyVO replyDetail(int rno);
	
	
	@Insert("INSERT INTO YJREPLY VALUES(yjReply_seq.nextval,#{name},#{content},#{title})")
	public void repelyInsert(ReplyVO vo);
	
	@Update("UPDATE YJREPLY SET content=#{content} where rno=#{rno}")
	public void replyUpdate(ReplyVO vo);
	
	
	@Delete("DELETE FROM YJREPLY WHERE rno=#{rno}")
	public void replyDelete(int rno);
}
