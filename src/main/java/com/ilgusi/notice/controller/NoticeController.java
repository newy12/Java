package com.ilgusi.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilgusi.notice.model.service.NoticeService;
import com.ilgusi.notice.model.vo.Notice;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService service;
	
	//공지사항 목록으로 이동 
	@RequestMapping("/noticeList.do")
	public String noticeList (Model model) {
		ArrayList<Notice> list = service.selectNoticeList();
		model.addAttribute("list", list);
		return "notice/noticeList";
	}
	
}
