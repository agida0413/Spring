package com.sist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

@Controller
public class GoodsController {

	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("goods/list.do")
	public String goods_list(String page,Model model) {
		if(page==null) {
			page="1";
		}
		int curpage =Integer.parseInt(page);
		int rowsize=20;
		int start = (curpage*rowsize)-rowsize;
		
		List<GoodsVO>list= dao.goodsListData(start);
		//Limit = > 0 번부터 시작 ,rownum =1 번
		int totalpage=dao.goodsTotalPage();
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("curpage",page);
		return "goods/list";
	}
	
}
