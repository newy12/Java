package com.ilgusi.chat.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.chat.model.dao.ChatDao;

@Service
public class ChatService {
	@Autowired
	private ChatDao dao;
}
