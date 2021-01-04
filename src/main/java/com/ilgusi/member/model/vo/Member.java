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
	private String introduce;
	private String enrollDate;
	private String brandName;
	private String contactTime;
	
	//줄바꿈
	public String getIntroductBr() {
		return introduce.replaceAll("\r\n", "<br>");
	}

	public Member(String mName) {
		super();
		this.mName = mName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}
	
}
