package com.sist.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/site/chat/chat-ws")
public class ChatServer {
//저장 ->접속자 정보저장 
	private static List<Session> users=Collections.synchronizedList(new ArrayList<Session>());
	//WebSocket에 존재하는 session
	
	//클라이언트가 접속을 했을때
	@OnOpen
	public void onOpen(Session session) {
		users.add(session);
		System.out.println("클라이언트접속:"+session.getId());
	}
	
	//클라이언트가 퇴장
	@OnClose
	public void onClose(Session session) {
		users.remove(session);
		System.out.println("클라이언트 퇴장"+session.getId());
	}
	
	//채팅= > 문자열 전송
	@OnMessage
	public void onMessage(String message,Session session) throws Exception {
		System.out.println(session.getId()+"님의 메시지:"+message);
		Iterator<Session> iter=users.iterator();
		while(iter.hasNext()) {
			iter.next().getBasicRemote().sendText(message);
		}
		
//		for(Session s:users) {
//			s.getBasicRemote().sendText(message);
//		}
	}
}
