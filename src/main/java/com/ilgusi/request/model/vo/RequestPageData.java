package com.ilgusi.request.model.vo;

import java.util.ArrayList;

import lombok.Data;

@Data
public class RequestPageData {
	private ArrayList<Request> list;
	private String pageNavi;
	private int totalCount;
	
}
