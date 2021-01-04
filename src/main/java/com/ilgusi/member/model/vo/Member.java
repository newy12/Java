package com.ilgusi.member.model.vo;

import lombok.Data;

@Data
public class Member {

	private int mNo;          
	private String mId;
	private String mPw;
	private String mName;
	private String mEmail;
	private String mPhone;
	private int mGrade;
	private int warningCount;
	private String introduct;
	private String enrollDate;
	private String brandName;
	private String contactTime;
	
	//줄바꿈
	public String getIntroductBr() {
		return introduct.replaceAll("\r\n", "<br>");
	}
}
