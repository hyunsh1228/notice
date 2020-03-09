package com.test.notice.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.notice.notice.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService service;
	//글목록 요청처리
	@RequestMapping("/notice/list")
	public ModelAndView list(ModelAndView mView, HttpServletRequest request) {
		service.getList(request); 
		mView.setViewName("notice/list");
		return mView;
	}
	
	@RequestMapping("/notice/insertform")
	public ModelAndView authInsertform(ModelAndView mView, HttpServletRequest request) {  
		//로그인 된 회원의 아이디 읽어오기
		String id=(String)request.getSession().getAttribute("id");
		mView.addObject("id", id);
		mView.setViewName("notice/insertform");
		return mView;
	}
	
	@RequestMapping("/notice/insert")
	public ModelAndView Insert(ModelAndView mView, HttpServletRequest request) {
		service.insert(request);
		mView.setViewName("notice/insert");
		return mView;
	}
		
	//글 자세히 보기 요청 처리
	@RequestMapping("/notice/detail")
	public String detail(HttpServletRequest request){
		service.getDetail(request);
		return "notice/detail";
	}
	
	@RequestMapping("/notice/delete")
	public ModelAndView delete(ModelAndView mView, HttpServletRequest request, HttpServletResponse response) {
		service.delete(request, response);
		mView.setViewName("notice/delete");
		return mView;
	}
	
	@RequestMapping("/notice/updateform")
	public ModelAndView updateform(ModelAndView mView, HttpServletRequest request, HttpServletResponse response) {
		service.updateform(request, response);
		mView.setViewName("notice/updateform");
		return mView;
	}
	@RequestMapping("/notice/update")
	public ModelAndView update(ModelAndView mView, HttpServletRequest request) {
		service.update(request);
		mView.setViewName("notice/update");
		return mView;
	}
	
}
