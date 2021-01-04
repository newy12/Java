package com.ilgusi.notice.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.notice.model.dao.NoticeDao;

@Service
public class NoticeService {
	@Autowired
	private NoticeDao dao;
}
