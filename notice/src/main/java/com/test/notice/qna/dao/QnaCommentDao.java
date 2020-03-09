package com.test.notice.qna.dao;

import com.test.notice.qna.dto.QnaCommentDto;

public interface QnaCommentDao {
	public void insert(QnaCommentDto dto);
	public int getSequence();
}
