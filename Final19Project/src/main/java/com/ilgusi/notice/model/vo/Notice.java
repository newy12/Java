package com.ilgusi.notice.model.vo;

import lombok.Data;

@Data
public class Notice {

	private int nNo;
	private String nTitle;
	private String nContent;
	private String filename;
	private String filepath;
	private String writeDate;
	
	//줄바꿈
	public String getNContentBr() {
		return nContent.replaceAll("\r\n", "<br>");
	}
}
