package com.test.notice.notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.notice.notice.dto.NoticeDto;

public interface NoticeService {
	public void getList(HttpServletRequest request);
	public void saveContent(NoticeDto dto);
	public void delete(HttpServletRequest request, HttpServletResponse response);
	public void updateform(HttpServletRequest request, HttpServletResponse response);
	public void update(HttpServletRequest request);
	public void addViewCount(HttpServletRequest request, int num);
	//글 자세히 보기 요청 처리
	public void getDetail(HttpServletRequest request);
}
