package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.service.BoardService;
import com.sist.vo.BoardVO;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	@GetMapping("board/list.do")
	public String boardList(String page,Model model) {
		
		return service.boardListData(page, model);
	}
	
	@GetMapping("board/insert.do")
	public String boardinsert() {
		
		return "board/insert";
	}
	
	@GetMapping("board/insert_ok.do")
	public String boardInsertOk(BoardVO vo)
	{
		return service.boardInsertOk(vo);
	}
}
