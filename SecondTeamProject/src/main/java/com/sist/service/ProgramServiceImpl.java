package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.OptionDAO;
import com.sist.dao.ProgramListDAO;
import com.sist.vo.OptionVO;
import com.sist.vo.ProgramVO;

@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	private ProgramListDAO pDao;
	@Autowired
	private OptionDAO oDao;

	@Override
	public List<OptionVO> stateOption() {
	
		return oDao.stateOption();
	}

	@Override
	public List<OptionVO> cityOption(String state) {
		
		return oDao.cityOption(state);
	}

	@Override
	public List<OptionVO> majorOption() {
		// TODO Auto-generated method stub
		return oDao.majorOption();
	}

	@Override
	public List<OptionVO> minorOption(String major_option) {
		// TODO Auto-generated method stub
		return oDao.minorOption(major_option);
	}

	
	//프로그램
	@Override
	public List<ProgramVO> programListData(Map map) {
		// TODO Auto-generated method stub
		return pDao.programListData(map);
	}

	@Override
	public int programTotalPage(Map map) {
		// TODO Auto-generated method stub
		return pDao.programTotalPage(map);
	}

	@Override
	public ProgramVO programDetailData(int vno) {
		// TODO Auto-generated method stub
		return pDao.programDetailData(vno);
	}

	
	
	
}
