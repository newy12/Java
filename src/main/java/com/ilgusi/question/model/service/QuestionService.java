package com.ilgusi.question.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.question.model.dao.QuestionDao;

@Service
public class QuestionService {
	@Autowired
	private QuestionDao dao;
}
