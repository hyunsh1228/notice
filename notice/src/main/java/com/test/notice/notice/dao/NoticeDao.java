package com.test.notice.notice.dao;

import java.util.List;

import com.test.notice.notice.dto.NoticeDto;

public interface NoticeDao {
	//글 전체의 갯수를 리턴하는 메소드
	public int getCount(NoticeDto dto);
	//글 목록을 리턴하는 메소드
	public List<NoticeDto> getList(NoticeDto dto);
	//새글을 추가하는 메소드
	public void insert(NoticeDto dto);
	//글 정보 얻어오기 
	public NoticeDto getData(NoticeDto dto);
	//글 조회수 1 증가 시키는 메소드
	public int addViewCount(int num);
	//글 정보를 삭제하는 메소드
	public int delete(int num);
	//num 인자를 전달 했을 때 글 하나의 정보를 리턴하는 메소드
	public NoticeDto getData(int num);
	//글 정보를 수정하는 메소드
	public int update(NoticeDto dto);
}
