package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.FoodService;
import com.sist.vo.FoodVO;
import com.sist.vo.MemberVO;
import com.sist.vo.ReplyVO;

@RestController
public class MainRestController {
@Autowired
private FoodService service;

@GetMapping(value="food/list_vue.do",produces = "text/plain;charset=UTF-8")
public String food_list(int page) throws JsonProcessingException {
	
	int rowSize=12;
	int start=(rowSize*page)-(rowSize-1);
	int end=rowSize*page;
	
	List<FoodVO>list=service.foodList(start, end);
	
	ObjectMapper mapper= new ObjectMapper();
	String json=mapper.writeValueAsString(list);
	
	return json;

}

@GetMapping(value="food/page_vue.do",produces = "text/plain;charset=UTF-8")
public String food_page(int page) throws JsonProcessingException {
	int totalpage=service.foodTotalpage();
	final int BLOCK=10;
	int startpage=((page-1)/BLOCK*BLOCK)+1;
	int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
	if(endpage>totalpage) {
		endpage=totalpage;
	}
	
	Map map=new HashMap();
	map.put("curpage", page);
	map.put("totalpage", totalpage);
	map.put("startpage", startpage);
	map.put("endpage", endpage);
	ObjectMapper mapper= new ObjectMapper();
	String json= mapper.writeValueAsString(map);
	
	return json;
}

@GetMapping(value="food/detail_vue.do",produces = "text/plain;charset=UTF-8")
public String food_detail(int fno,HttpSession session) throws JsonProcessingException {
	FoodVO vo =service.foodDetail(fno);
	String id=(String)session.getAttribute("id");
	vo.setSessionId(id);
	
	ObjectMapper mapper= new ObjectMapper();
	String json= mapper.writeValueAsString(vo);
	return json;
}


@GetMapping(value="member/login_vue.do",produces = "text/plain;charset=UTF-8")
public String login(String id,String pwd,HttpSession session) throws JsonProcessingException {
	MemberVO vo=service.isLogin(id, pwd);
	
	if(vo.getMsg().equals("OK")) {
		session.setAttribute("id", vo.getId());
		session.setAttribute("name",vo.getName());
		}
	
	ObjectMapper mapper= new ObjectMapper();
	String json=mapper.writeValueAsString(vo);
	
	return json;
}




@GetMapping(value="member/logout_vue.do",produces = "text/plain;charset=UTF-8")
public void logout(HttpSession session) throws JsonProcessingException {
	session.invalidate();
}


//=> 댓글리스트 재사용을 위함
public String commonsData(int fno) throws Exception
{
List<ReplyVO> list=service.replyListData(fno);
ObjectMapper mapper=new ObjectMapper();
String json=mapper.writeValueAsString(list);
return json;
}

@GetMapping(value="reply/list_vue.do",produces = "text/plain;charset=UTF-8")
public String reply_list_vue(int fno) throws Exception
{
return commonsData(fno);
}

@GetMapping(value="reply/insert_vue.do",produces = "text/plain;charset=UTF-8")
public String reply_insert(int fno,String msg,HttpSession session) throws Exception
{
String id=(String)session.getAttribute("id");
String name=(String)session.getAttribute("name");
ReplyVO vo=new ReplyVO();
vo.setFno(fno);
vo.setId(id);
vo.setName(name);
vo.setMsg(msg);
service.replyInsert(vo);
return commonsData(fno);
}


@GetMapping(value="reply/delete_vue.do",produces = "text/plain;charset=UTF-8")
public String replt_delete(int rno,int fno) throws Exception {
	service.replyDelete(rno);
	
	return commonsData(fno); 
}

@GetMapping(value="reply/update_vue.do",produces = "text/plain;charset=UTF-8")
public String replt_update(ReplyVO vo) throws Exception {
	
	
	service.replyUpdate(vo);
	return commonsData(vo.getFno()); 
}
}
