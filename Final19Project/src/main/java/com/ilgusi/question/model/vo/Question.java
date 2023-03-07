package com.ilgusi.question.model.vo;

import lombok.Data;

@Data
public class Question {
	private int qNo;				//문의번호
	private int mNo;				//회원 번호(작성자)
	private String mId;				//회원 아이디(작성자)
	private String qTitle;			//문의 제목
	private String qContent;		//문의 내용
	private int secretStatus;		//비밀여부
	private String writeDate;		//작성 날짜
	private int answerStatus;		//답변여부
	private String answerContent;	//답변 내용
	private String answerDate;		//답변 날짜
	private String filePath;		//파일이름(물리적)
	
	//줄바꿈
	public String getQContentBr() {
		return qContent.replaceAll("\r\n", "<br>");
	}
	public String getAnswerContentBr() {
		return answerContent.replaceAll("\r\n", "<br>");
	}
}
