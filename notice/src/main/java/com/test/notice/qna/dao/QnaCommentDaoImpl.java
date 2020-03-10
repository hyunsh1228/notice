package com.test.notice.qna.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.notice.qna.dto.QnaCommentDto;

@Repository
public class QnaCommentDaoImpl implements QnaCommentDao{
	@Autowired
	private SqlSession session;


	@Override
	public List<QnaCommentDto> getList(int ref_group) {
		
		return session.selectList("qnaComment.getList", ref_group);
	}

	@Override
	public void delete(int num) {
		session.update("qnaComment.delete", num);
	}

	@Override
	public void insert(QnaCommentDto dto) {
		session.insert("qnaComment.insert", dto);
	}
	//저장할 댓글의 글번호를 리턴하는 메소드 
	@Override
	public int getSequence() {
		//시퀀스 값을 얻어내서 
		int seq=session.selectOne("qnaComment.getSequence");
		//리턴해준다.
		return seq;
	}

	@Override
	public void update(QnaCommentDto dto) {
		session.update("qnaComment.update", dto);
	}
}
