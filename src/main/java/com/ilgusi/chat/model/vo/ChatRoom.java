package com.ilgusi.chat.model.vo;

import lombok.Data;

@Data
public class ChatRoom {
	private int sNo;
	private String userId;
	private String freeId;

	public ChatRoom(int sNo, String userId, String freeId) {
		super();
		this.sNo = sNo;
		this.userId = userId;
		this.freeId = freeId;
	}

}
