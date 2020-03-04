package com.test.notice.qna.dao;

import java.util.List;

import com.test.notice.qna.dto.QnaDto;

public interface QnaDao {
	//글 전체의 갯수를 리턴하는 메소드
	public int getCount(QnaDto dto);
	//글 목록을 리턴하는 메소드
	public List<QnaDto> getList(QnaDto dto);
	//새글을 추가하는 메소드
	public int insert(QnaDto dto);
	//글 정보 얻어오기 
	public QnaDto getData(QnaDto dto);
	//글 조회수 1 증가 시키는 메소드
	public int addViewCount(int num);
	//글 정보를 삭제하는 메소드
	public int delete(int num);
	//num 인자를 전달 했을 때 글 하나의 정보를 리턴하는 메소드
	public QnaDto getData(int num);
	//글 정보를 수정하는 메소드
	public int update(QnaDto dto);
}
