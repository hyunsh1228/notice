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
	
	//글 목록을 리턴하는 메소드
	@Override
	public List<QnaDto> getList(QnaDto dto) {
		List<QnaDto> list=session.selectList("qna.getList", dto);
		return list;
	}
	//새글을 저장하는 메소드
	@Override
	public int insert(QnaDto dto) {
		int num=session.insert("qna.insert", dto);
		return num;
	}
	//글 전체의 갯수를 리턴하는 메소드
	@Override
	public int getCount(QnaDto dto) {

		return session.selectOne("qna.getCount", dto);
	}
	//num 인자를 전달 했을 때 글 하나의 정보를 리턴하는 메소드
	@Override
	public QnaDto getData(int num) {
	
		return session.selectOne("qna.getData2", num);
	}
	//글 조회수 1 증가 시키는 메소드
	@Override
	public int addViewCount(int num) {
		
		return session.update("qna.addViewCount", num);
	}
	//글 정보를 수정하는 메소드
	@Override
	public int update(QnaDto dto) {
		session.update("qna.update", dto);
		return session.update("qna.update", dto);
	}
	//글 정보를 삭제하는 메소드
	@Override
	public int delete(int num) {
		
		return session.delete("qna.delete", num);
	}
	//글 정보 얻어오기 
	@Override
	public QnaDto getData(QnaDto dto) {
		// TODO Auto-generated method stub
		return session.selectOne("qna.getData", dto);
	}
}
