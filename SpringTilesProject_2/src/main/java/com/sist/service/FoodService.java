package com.sist.service;

import java.util.List;

import com.sist.vo.FoodVO;
import com.sist.vo.MemberVO;
import com.sist.vo.ReplyVO;

public interface FoodService {
	public List<FoodVO> foodList( int start, int end);
	public int foodTotalpage();
	public FoodVO foodDetail(int fno);
	
	
	
	//멤버관련 
	
	public MemberVO isLogin(String id,String pwd);
	
	
	
	public List<ReplyVO> replyListData(int fno);
	
	public void replyInsert(ReplyVO vo) ;
	
	public void replyDelete(int rno) ;
	
	public void replyUpdate(ReplyVO vo);
}
