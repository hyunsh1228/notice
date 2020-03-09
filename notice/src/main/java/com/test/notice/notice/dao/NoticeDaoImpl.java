package com.test.notice.notice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.notice.notice.dto.NoticeDto;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	@Autowired
	private SqlSession session;
	
	//글 목록을 리턴하는 메소드
	@Override
	public List<NoticeDto> getList(NoticeDto dto) {
		List<NoticeDto> list=session.selectList("notice.getList", dto);
		return list;
	}
	//새글을 저장하는 메소드
	@Override
	public void insert(NoticeDto dto) {
		session.insert("notice.insert", dto);

	}
	//글 전체의 갯수를 리턴하는 메소드
	@Override
	public int getCount(NoticeDto dto) {

		return session.selectOne("notice.getCount", dto);
	}
	//num 인자를 전달 했을 때 글 하나의 정보를 리턴하는 메소드
	@Override
	public NoticeDto getData(int num) {
	
		return session.selectOne("notice.getData2", num);
	}
	//글 조회수 1 증가 시키는 메소드
	@Override
	public int addViewCount(int num) {
		
		return session.update("notice.addViewCount", num);
	}
	//글 정보를 수정하는 메소드
	@Override
	public int update(NoticeDto dto) {
		session.update("notice.update", dto);
		return session.update("notice.update", dto);
	}
	//글 정보를 삭제하는 메소드
	@Override
	public int delete(int num) {
		
		return session.delete("notice.delete", num);
	}
	//글 정보 얻어오기 
	@Override
	public NoticeDto getData(NoticeDto dto) {
		
		return session.selectOne("notice.getData", dto);
	}
}
