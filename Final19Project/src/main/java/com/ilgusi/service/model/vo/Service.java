package com.ilgusi.service.model.vo;

import lombok.Data;

@Data
public class Service {
	private int sNo;			//서비스 번호
	private String mId;			//회원 아이디(작성자
	private String sTitle;		//서비스 제목
	private int sPrice;			//서비스 가격
	private String sContent;	//서비스 내용
	private String sArea;		//서비스 지역
	private String sImg;		//섬네일 이미지
	private float sRate;			//서비스 평점
	private int mainCategory;	//메인카테고리 번호
	private int subCategory;	//서브카테고리 번호
	private int workingDate;	//예상 작업일
	private int workingCount;	//진행한 작업 수
	private String writeDate;	//작성 날짜
	private char deleteStatus;	//삭제 여부
	private char adminApproval;	//승인 여부
	
	//(문정) 추가
	private String mainCategoryName;
	private String subCategoryName;
	private String sPriceTxt;    //천원단위로 ,찍은 텍스트 저장
	
	//(다솜)추가
	private String brandName;
	
	//줄바꿈
	public String getSContentBr() {
		return sContent.replaceAll("\r\n", "<br>");
	}
}
