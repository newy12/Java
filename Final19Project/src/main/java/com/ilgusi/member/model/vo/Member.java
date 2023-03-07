package com.ilgusi.member.model.vo;

import lombok.Data;

@Data
public class Member {
	// 변수명 대문자로
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
	private int buyingCount;
	private int sellingCount;
	// 줄바꿈
	public String getIntroduceBr() {
		return introduce.replaceAll("\r\n", "<br>");
	}

}
