package com.test.notice.qna.dao;

import java.util.List;

import com.test.notice.qna.dto.QnaDto;

public interface QnaDao {
	//글의 갯수 
	public int getCount(QnaDto dto);
	//글의 목록
	public List<QnaDto> getList(QnaDto dto);
	//글 추가 
	public void insert(QnaDto dto);
	//글 정보 얻어오기 
	public QnaDto getData(QnaDto dto);
	//조회수 증가 시키기 
	public void addViewCount(int num);
	//글 삭제 
	public void delete(int num);
	//글 하나의 정보 
	public QnaDto getData(int num);
	//글 수정 
	public void update(QnaDto dto);
}
