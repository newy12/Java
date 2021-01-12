package com.ilgusi.question.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.question.model.dao.QuestionDao;
import com.ilgusi.question.model.vo.Question;

@Service
public class QuestionService {
	@Autowired
	private QuestionDao dao;

	public List<Question> selectQuestionList() {
		return dao.selectQuestionList();
	}

	public int insertQuestion(Question q) {
		return dao.insertQuestion(q);
	}

	public Question selectOneQuestion(int qNo) {
		return dao.selectOneQuestion(qNo);
	}
}
