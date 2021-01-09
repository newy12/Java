package com.ilgusi.service.model.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Join {
	private String mId;
	private String sTitle;
	private int sPrice;
	private String sContent;
	private String sArea;
	private int mainCategory;
	private int subCategory;
	private int workingDate;
	private int mNo;  
	private int sRate;
	private String mName;
	private String mEmail;
	private String mPhone;
	private String introduce;  
	private String enrollDate;
	private String brandName;  //브랜드명
	private String contactTime;//연락가능시간
	private List<Service> serviceList;		 //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private List<ServiceReview> reviewList;  //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다. 
	private ArrayList<ServiceFile> fileList ; //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private String sImg;
	//
	//줄바꿈
	
	
}
