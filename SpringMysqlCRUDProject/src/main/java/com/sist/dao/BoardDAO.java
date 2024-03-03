package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.BoardMapper;
import com.sist.vo.BoardVO;

@Repository
public class BoardDAO {
@Autowired
	private BoardMapper mapper;

public List<BoardVO> boardListData(int start) {
	return mapper.boardListData(start);
}
public int boardTotalPage() {
	return mapper.boardTotalPage();
}

public void boardInsert(BoardVO vo) {
	 mapper.boardInsert(vo);
}
}
