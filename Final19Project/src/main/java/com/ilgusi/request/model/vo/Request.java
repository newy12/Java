package com.ilgusi.request.model.vo;

import lombok.Data;

@Data
public class Request {
	private int reqNo;			//의뢰 번호
	private String mId;			//의뢰 작성자 ID
	private String reqTitle;	//의뢰 타이틀
	private String reqContent;	//의뢰 내용
	private int reqStatus;		//의뢰 상태
	private String filename;	//파일 이름
	private String filepath;	//파일 경로
	private String writeDate;	//작성일
	
	//줄바꿈
	public String getReqContentBr() {
		return reqContent.replaceAll("\r\n", "<br>");
	}
}
