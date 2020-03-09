package com.test.notice.qna.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.notice.qna.dto.QnaDto;

@Repository
public class QnaDaoImpl implements QnaDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int getCount(QnaDto dto) {
		return session.selectOne("qna.getCount", dto);
	}

	@Override
	public List<QnaDto> getList(QnaDto dto) {
		return session.selectList("qna.getList", dto);
	}

	@Override
	public void insert(QnaDto dto) {
		session.selectList("qna.getList", dto);
		
	}

	@Override
	public QnaDto getData(QnaDto dto) {
		return session.selectOne("qna.getData", dto);
	}

	@Override
	public void addViewCount(int num) {
		session.update("qna.addViewCount", num);
	}

	@Override
	public void delete(int num) {
		session.delete("qna.delete", num);
	}

	@Override
	public QnaDto getData(int num) {
		return session.selectOne("qna.getData2", num);
	}

	@Override
	public void update(QnaDto dto) {
		session.update("qna.update", dto);
	}

}
