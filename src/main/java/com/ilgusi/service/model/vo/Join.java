package com.ilgusi.service.model.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Join {
	private String sTitle;		//서비스 제목
	private int sPrice;			//서비스 가격
	private String sContent;	//서비스 내용
	private String sArea;		//서비스 지역
	private String sImg;		//섬네일 이미지
	private int sRate;			//서비스 평점
	private int mainCategory;	//메인카테고리 번호
	private int subCategory;	//서브카테고리 번호
	private int workingDate;	//예상 작업일
	private int workingCount;	//진행한 작업 수
	private char deleteStatus;	//삭제 여부
	private char adminApproval;	//승인 여부
	private int mNo;          
	private String mPw;
	private String mName;
	private String mEmail;
	private String mPhone;
	private int mGrade;
	private int warningCount;
	private String introduce;
	private String enrollDate;
	private String brandName;
	private String contactTime;  //조인 나와라
	private List<ServiceReview> reviewList;
	//줄바꿈
	public String getSContentBr() {
		return sContent.replaceAll("\r\n", "<br>");
	}
	
}
