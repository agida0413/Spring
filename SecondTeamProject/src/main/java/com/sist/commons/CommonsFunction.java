package com.sist.commons;

import org.springframework.stereotype.Component;

@Component
public class CommonsFunction {

	public int start(int rowSize,int page) {
		
		return (rowSize*page)-(rowSize-1);
	}
	
public int end(int rowSize,int page) {
		
		return rowSize*page;
	}

public int startPage(int BLOCK,int page) {
	
	return ((page-1)/BLOCK*BLOCK)+1;
}

public int endPage(int BLOCK,int page,int totalPage) {
	
	 int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
	 
	 if (endPage>totalPage) {
     	endPage=totalPage;
     }
	return endPage;
}
}
