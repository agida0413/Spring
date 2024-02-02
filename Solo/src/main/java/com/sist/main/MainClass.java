package com.sist.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.ReplyDAO;
import com.sist.dao.ReplyVO;

public class MainClass {
public static void main(String[] args) {
	ApplicationContext app=new ClassPathXmlApplicationContext("application.xml");
	ReplyDAO dao=(ReplyDAO)app.getBean("dao");
	
	Scanner scan=new Scanner(System.in);
	while(true) {
		System.out.println("-----------------------------");

		System.out.println("1.목록:");
		System.out.println("2.댓글입력:");
		System.out.println("3.댓글상세보기");
		System.out.println("4.댓글수정");
		System.out.println("5.댓글삭제");
		
		System.out.println("-----------------------------");
		int menu=scan.nextInt();	
		if(menu==1) {
		List<ReplyVO> list=dao.replyList();
		for (ReplyVO vo : list) {
			System.out.println(vo.getRno()+" "+vo.getTitle()+" "+vo.getName());
		}
		}
		else if(menu==2) {
			System.out.println("이름입력:");
			String name=scan.next();
			System.out.println("내용입력:");
			String content=scan.next();
			System.out.println("제목입력:");
			String title=scan.next();
			
			ReplyVO vo=new ReplyVO();
			vo.setContent(content);
			vo.setName(name);
			vo.setTitle(title);
			
			dao.repelyInsert(vo);
			
		}
		
		else if(menu==3) {
			System.out.println("상세보기할번호입력:");
			int rno=scan.nextInt();
			ReplyVO vo=dao.replyDetail(rno);
			System.out.println("번호:"+vo.getRno());
			System.out.println("이름:"+vo.getName());
			System.out.println("내용:"+vo.getContent());
			System.out.println("제목:"+vo.getTitle());
			
			
		}
		else if(menu==4) {
			System.out.println("수정번호입력:");
			int rno=scan.nextInt();
			System.out.println("수정내용입력:");
			String content =scan.next();
			ReplyVO vo=new ReplyVO();
			vo.setRno(rno);
			vo.setContent(content);
			dao.replyUpdate(vo);
		}
		else if(menu==5) {
	
			System.out.println("삭제번호입력:");
			int rno=scan.nextInt();
			
			dao.replyDelete(rno);
		}
		
	}
}
}
