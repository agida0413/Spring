package com.sist.mapper;

import java.util.List;

import com.sist.vo.BoardVO;

public interface BoardMapper {

	public List<BoardVO> boardListData(int start);
	
	public int boardTotalPage();
	
public void boardInsert(BoardVO vo);
}
