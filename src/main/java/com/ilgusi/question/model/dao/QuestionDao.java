package com.ilgusi.question.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.question.model.vo.Question;

@Repository
public class QuestionDao {
	@Autowired
	private SqlSessionTemplate session;

	public List<Question> selectQuestionList() {
		return session.selectList("question.selectQuestionList");
	}

	public int insertQuestion(Question q) {
		return session.insert("question.insertQuestion", q);
	}

	public Question selectOneQuestion(int qNo) {
		return session.selectOne("question.selectOneQuestion",qNo);
	}
}
