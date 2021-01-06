package com.ilgusi.chat.controller;

import java.util.HashMap;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component("startChat")
public class startChat extends TextWebSocketHandler {
	//접속한 클라이언트 정보를 저장할 변수
	//접속한 회원의 아이디를 key, 접속 session을 value로 한 HashMap
	private HashMap<String, WebSocketSession> chatMembers;

	public startChat() {
		super();
		chatMembers = new HashMap<String, WebSocketSession>();
	}

	//클라이언트가 접속했을 때 호출되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("클라이언트 접속!!");

	}

	//클라이언트가 메세지를 보냈을때 호출되는 메소드
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String rexMsg = message.getPayload();
		JsonParser parser = new JsonParser();
		JsonObject msg = (JsonObject) parser.parse(rexMsg);
		String type = msg.get("type").getAsString();
		String data = msg.get("data").getAsString();
		if (type.equals("register")) {
			chatMembers.put(data, session);
			Set<String> keys = chatMembers.keySet();
			String welcomeMsg = "<p class='text-center'> ------- [" + data + "] 님이 접속 -------</p>";
			for (String key : keys) {
				chatMembers.get(key).sendMessage(new TextMessage(welcomeMsg));
			}
		} else {
			Set<String> keys = chatMembers.keySet();
			for (String key : keys) {
				WebSocketSession sendSession = chatMembers.get(key);
				if (!sendSession.equals(session)) {
					sendSession.sendMessage(new TextMessage(data));
				}
			}
		}
	}

	//클라이언트의 접속이 끊어졌을때 호출되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("클라이언트 접속 해제!!");
		Set<String> keys = chatMembers.keySet();
		String outMember = null;
		for (String key : keys) {
			WebSocketSession currentSession = chatMembers.get(key);
			if (currentSession.equals(session)) {
				outMember = key;
				chatMembers.remove(key);
				break;
			}
		}
		keys = chatMembers.keySet();
		String byeMsg = "<p class='text-center'> -------- [" + outMember + "] 퇴장 --------</p>";
		for (String key : keys) {
			chatMembers.get(key).sendMessage(new TextMessage(byeMsg));
		}
	}
}
