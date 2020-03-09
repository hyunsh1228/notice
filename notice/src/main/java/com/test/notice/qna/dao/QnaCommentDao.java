package com.test.notice.qna.dao;

import java.util.List;

import com.test.notice.qna.dto.QnaCommentDto;

public interface QnaCommentDao {
	public List<QnaCommentDto> getList(int ref_group);
	public void delete(int num);
	public void insert(QnaCommentDto dto);
	public int getSequence();
	public void update(QnaCommentDto dto);
}