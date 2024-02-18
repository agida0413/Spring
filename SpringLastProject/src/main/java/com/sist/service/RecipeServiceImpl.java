package com.sist.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.*;
@Service
// mapper => dao => service => serviceimpl (의존성이 낮은 프로그램 => 다른 클래스 영향이 없게 제작)
// 유지보수 (스프링 , mvc)
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    private RecipeDAO rDao;

	@Override
	public List<RecipeVO> recipeHome12() {
		// TODO Auto-generated method stub
		return rDao.recipeHome12();
	}

	@Override
	public List<ChefVO> chefHome12() {
		// TODO Auto-generated method stub
		return rDao.chefHome12();
	}

	@Override
	public int recipeCount() {
		// TODO Auto-generated method stub
		return rDao.recipeCount();
	}

	@Override
	public List<RecipeVO> recipeListData(int start, int end) {
		// TODO Auto-generated method stub
		return rDao.recipeListData(start, end);
	}

	@Override
	public int recipeTotalPage() {
		// TODO Auto-generated method stub
		return rDao.recipeTotalPage();
	}

	@Override
	public List<ChefVO> chefListData(int start, int end) {
		// TODO Auto-generated method stub
		return rDao.chefListData(start, end);
	}

	@Override
	public int chefTotalPage() {
		// TODO Auto-generated method stub
		return rDao.chefTotalPage();
	}

	@Override
	public List<RecipeVO> chefDetailData(Map map) {
		// TODO Auto-generated method stub
		return rDao.chefDetailData(map);
	}

	@Override
	public int chefDetailTotalPage(int cno) {
		// TODO Auto-generated method stub
		return rDao.chefDetailTotalPage(cno);
	}

	@Override
	public List<RecipeVO> chefDetailFindData(Map map) {
		// TODO Auto-generated method stub
		return rDao.chefDetailFindData(map);
	}

	@Override
	public int chefDetailFindTotalPage(Map map) {
		// TODO Auto-generated method stub
		return rDao.chefDetailFindTotalPage(map);
	}

	@Override
	public RecipeDetailVO recipeDetailData(int no) {
		// TODO Auto-generated method stub
		return rDao.recipeDetailData(no);
	}

	@Override
	public List<GoodsVO> recipeGoodsData(String goods_name) {
		// TODO Auto-generated method stub
		return rDao.recipeGoodsData(goods_name);
	}
   
   
}