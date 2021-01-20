package com.ilgusi.question.model.dao;

import java.util.HashMap;
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
	public List<Question> selectQuestionList(int begin,int end) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("begin", begin);
		map.put("end", end);
		return session.selectList("question.selectQuestionList",map);
	}
	public List<Question> selectQuestionList(int begin,int end,int type,String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("type", type);
		System.out.println(keyword +"dd");
		map.put("keyword", keyword);
		System.out.println(map.get("keyword")+"ww");
		return session.selectList("question.selectQuestionList",map);
	}

	public int insertQuestion(Question q) {
		return session.insert("question.insertQuestion", q);
	}

	public Question selectOneQuestion(int qNo) {
		return session.selectOne("question.selectOneQuestion",qNo);
	}
	public int selectQuestionCount() {
		return session.selectOne("question.selectCount");
	}
	public int selectQuestionCount(int type,String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("keyword", keyword);
		return session.selectOne("question.selectCount",map);
	}
	public int updateAnswer(Question q) {
		return session.update("question.updateAnswer",q);
	}
	public int updateQuestion(Question q) {
		return session.update("question.updateQuestion",q);
	}
	public int deleteQuestion(int qNo) {
		return session.delete("question.deleteQuestion", qNo);
	}
}
