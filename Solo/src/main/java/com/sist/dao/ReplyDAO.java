package com.sist.dao;

import java.util.List;

public class ReplyDAO {
private ReplyMapper mapper;

public void setMapper(ReplyMapper mapper) {
	this.mapper = mapper;
}

public List<ReplyVO> replyList(){
	return mapper.replyList();
}

public ReplyVO replyDetail(int rno) {
	
	return mapper.replyDetail(rno);
}

public void repelyInsert(ReplyVO vo) {
	
	mapper.repelyInsert(vo);
}

public void replyUpdate(ReplyVO vo) {
	mapper.replyUpdate(vo);
}


public void replyDelete(int rno) {
mapper.replyDelete(rno);	
}

}
