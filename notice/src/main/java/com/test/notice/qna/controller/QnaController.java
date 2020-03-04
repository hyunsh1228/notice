package com.test.notice.qna.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.notice.qna.service.QnaService;

@Controller
public class QnaController {
	@Autowired
	private QnaService service;
	//글목록 요청처리
	@RequestMapping("/qna/list")
	public ModelAndView list(ModelAndView mView, HttpServletRequest request) {
		// HttpServletRequest 객체를 서비스에 넘겨 주면서
		// 비즈니스 로직을 수행하고 
		service.getList(request); //HttpServletRequest 객체는 요청한 클라이언트의 정보를 확인할수 있는기능이 들어 있다.
		// view page 로 forward 이동해서 글 목록 출력하기 
		mView.setViewName("qna/list");
		return mView;
	}
	
	@RequestMapping("/qna/insertform")
	public ModelAndView authInsertform(ModelAndView mView, HttpServletRequest request) {  
		//로그인 된 회원의 아이디 읽어오기
		String id=(String)request.getSession().getAttribute("id");
		mView.addObject("id", id);
		mView.setViewName("qna/insertform");
		return mView;
	}
	
	@RequestMapping("/qna/insert")
	public ModelAndView authInsert(ModelAndView mView, HttpServletRequest request) {
		service.insert(request);
		mView.setViewName("qna/insert");
		return mView;
	}
	
	//글 자세히 보기 요청 처리
	@RequestMapping("/qna/detail")
	public String detail(HttpServletRequest request){
		service.getDetail(request);
		//view page 로 forward 이동해서 글 자세히 보기 
		return "qna/detail";
	}
	
	@RequestMapping("/qna/delete")
	public ModelAndView delete(ModelAndView mView, HttpServletRequest request, HttpServletResponse response) {
		service.delete(request, response);
		mView.setViewName("qna/delete");
		return mView;
	}
	
	@RequestMapping("/qna/updateform")
	public ModelAndView updateform(ModelAndView mView, HttpServletRequest request, HttpServletResponse response) {
		service.updateform(request, response);
		mView.setViewName("qna/updateform");
		return mView;
	}
	@RequestMapping("/qna/update")
	public ModelAndView update(ModelAndView mView, HttpServletRequest request) {
		service.update(request);
		mView.setViewName("qna/update");
		return mView;
	}
	
}
