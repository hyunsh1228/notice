package com.test.notice.qna.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.notice.qna.dto.QnaCommentDto;

@Repository
public class QnaCommentDaoImpl implements QnaCommentDao{
	@Autowired
	private SqlSession session;

	@Override
	public void insert(QnaCommentDto dto) {
		session.insert("qnaComment.insert", dto);
	}

	@Override
	public int getSequence() {
		int seq=session.selectOne("qnaComment.getSequence");
		//리턴해준다.
		return seq;
	}

	
}
