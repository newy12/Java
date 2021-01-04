package com.ilgusi.notice.model.vo;

import java.util.ArrayList;

import lombok.Data;
@Data
public class NoticePageData {
	private ArrayList<Notice> list;
	private String pageNavi;
}
