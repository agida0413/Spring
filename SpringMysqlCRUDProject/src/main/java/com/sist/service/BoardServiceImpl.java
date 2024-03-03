package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO dao;

	@Override
	public String boardListData(String page,Model model) {
		// TODO Auto-generated method stub
	if(page==null) {
		page="1";
	}
	int curpage=Integer.parseInt(page);
	int rowsize=10;
	int start=(rowsize*curpage)-rowsize;
	List<BoardVO> list = dao.boardListData(start);
	int totalpage=dao.boardTotalPage();
	model.addAttribute("list",list);
	model.addAttribute("curpage",curpage);
	model.addAttribute("totalpage",totalpage);
	
	return "board/list";
	}
	
	
	public String boardInsertOk(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do";
	}

	
	
}
