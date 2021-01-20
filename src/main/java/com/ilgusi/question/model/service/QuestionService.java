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
	//일정 갯수만
	public List<Question> selectQuestionList(int begin,int end) {
		return dao.selectQuestionList(begin,end);
	}
	//일정갯수 + 검색 결과만
	public List<Question> selectQuestionList(int begin,int end,int type,String keyword) {
		return dao.selectQuestionList(begin,end,type,keyword);
	}
	public int insertQuestion(Question q) {
		return dao.insertQuestion(q);
	}

	public Question selectOneQuestion(int qNo) {
		return dao.selectOneQuestion(qNo);
	}
	public int selectQuestionCount() {
		return dao.selectQuestionCount();
	}
	public int selectQuestionCount(int type,String keyword) {
		return dao.selectQuestionCount(type,keyword);
	}
	public int selectMaxPageCount(int numPerPage,int listCount) {
		int maxPageCount = listCount / numPerPage;
		
		if(listCount % numPerPage > 0)
			maxPageCount++;
		
		return maxPageCount;
	}
	public int updateAnswer(Question q) {
		return dao.updateAnswer(q);
	}
	public int updateQuestion(Question q) {
		return dao.updateQuestion(q);
	}
	public int deleteQuestion(int qNo) {
		return dao.deleteQuestion(qNo);
	}
}
