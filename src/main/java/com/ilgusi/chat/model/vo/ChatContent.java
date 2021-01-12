package com.ilgusi.chat.model.vo;

import lombok.Data;

@Data
public class ChatContent {
	private int ccNo;			//채팅내용 고유번호
	private int cNo;			//채팅 번호
	private String mId;			//보낸 사람 아이디
	private String cDate;
	private String cTime;		//보낸 시간
	private String cContent;	//채팅 내용
	private int readStatus;		//읽음 여부

	//줄바꿈
	public String getCContentBr() {
		return cContent.replaceAll("\r\n", "<br>");
	}

}
