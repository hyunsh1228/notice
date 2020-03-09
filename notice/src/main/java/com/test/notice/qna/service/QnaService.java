package com.test.notice.qna.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.test.notice.qna.dto.QnaCommentDto;
import com.test.notice.qna.dto.QnaDto;

public interface QnaService {
	public void getList(HttpServletRequest request);
	public void saveContent(QnaDto dto);
	public void getDetail(HttpServletRequest request);
	public void deleteContent(int num, HttpServletRequest request);
	//원글 수정폼 출력에 필요한 메소드 
	public void getUpdateData(ModelAndView mView, int num);
	//원글 수정하는 메소드 
	public void updateContent(QnaDto dto);	
	//댓글 저장하는 메소드
	public void saveComment(HttpServletRequest request);
	//댓글 삭제하는 메소드
	public void deleteComment(int num);
	//댓글 수정하는 메소드
	public void updateComment(QnaCommentDto dto);
}
