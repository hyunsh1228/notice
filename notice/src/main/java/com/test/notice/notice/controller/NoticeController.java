package com.test.notice.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.test.notice.notice.dto.NoticeDto;
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
	public ModelAndView authInsertform(HttpServletRequest request) {  
		return new ModelAndView("notice/insertform");
	}
	
	@RequestMapping(value="/notice/insert", method=RequestMethod.POST)
	public ModelAndView Insert(HttpServletRequest request, @ModelAttribute NoticeDto dto) {
				String writer=(String)
						request.getSession().getAttribute("id");
				//CafeDto 객체에 담고 
				dto.setWriter(writer);
				//서비스를 이용해서 DB 에 저장
				service.saveContent(dto);
				//글 목록 보기로 리다일렉트 이동 
				return new ModelAndView("redirect:/notice/list.do");
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
