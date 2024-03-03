package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public interface BoardService {

	
	public String boardListData(String page, Model model);
	
	public String boardInsertOk(BoardVO vo);
	
 }
