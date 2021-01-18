package com.ilgusi.service.model.vo;

import java.util.ArrayList;

import lombok.Data;
@Data
public class ServicePageData {
	
	private ArrayList<Service> list;
	private String pageNavi;
	
	public ServicePageData(ArrayList<Service> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	
	
}
