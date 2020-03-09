package com.test.notice.qna.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.test.notice.qna.dto.QnaDto;
import com.test.notice.qna.service.QnaService;


@Controller
public class QnaController {
	@Autowired
	private QnaService service;
	
	//글목록 요청처리
	@RequestMapping("/qna/list")
	public ModelAndView list(HttpServletRequest request){
		// HttpServletRequest 객체를 서비스에 넘겨 주면서
		// 비즈니스 로직을 수행하고 
		service.getList(request);
		
		// view page 로 forward 이동해서 글 목록 출력하기 
		return new ModelAndView("qna/list");
	}	
	//새글 추가 폼 요청 처리
	@RequestMapping("/qna/insertform")
	public ModelAndView authInsertform
			(HttpServletRequest request){
		
		return new ModelAndView("qna/insertform");
	}
	
	//새글 추가 요청 처리
	@RequestMapping(value="/qna/insert", method=RequestMethod.POST)
	public ModelAndView authInsert(HttpServletRequest request,
			@ModelAttribute QnaDto dto){
		//세션에 있는 글작성자의 아이디
		String writer=(String)
				request.getSession().getAttribute("id");
		//CafeDto 객체에 담고 
		dto.setWriter(writer);
		//서비스를 이용해서 DB 에 저장
		service.saveContent(dto);
		//글 목록 보기로 리다일렉트 이동 
		return new ModelAndView("redirect:/qna/list.do");
	}
	//글 자세히 보기 요청 처리
	@RequestMapping("/qna/detail")
	public String detail(HttpServletRequest request){
		service.getDetail(request);
		//view page 로 forward 이동해서 글 자세히 보기 
		return "qna/detail";
	}
	//원글 삭제 요청 처리
	@RequestMapping("/qna/delete")
	public ModelAndView 
		authDelete(HttpServletRequest request,
				@RequestParam int num){
		//서비스를 이용해서 글을 삭제하기 
		service.deleteContent(num, request);
		//글 목록 보기로 리다일렉트 이동 
		return new ModelAndView("redirect:/qna/list.do");
	}
	@RequestMapping("/qna/updateform")
	public ModelAndView 
		authUpdateform(HttpServletRequest request, 
			@RequestParam int num,
			ModelAndView mView){
		//서비스를 이용해서 수정할 글정보를 ModelAndView
		//객체에 담고
		service.getUpdateData(mView, num);
		//view page 로 forward 이동해서 수정폼 출력
		mView.setViewName("qna/updateform");
		return mView;
	}
	
	//원글 수정 반영 요청 처리
	@RequestMapping(value="/qna/update",
			method=RequestMethod.POST)
	public ModelAndView 
		authUpdate(HttpServletRequest request,
				@ModelAttribute QnaDto dto){
		//서비스를 이용해서 수정 반영한다.
		service.updateContent(dto);
		
		//글 자세히 보기로 리다일렉트 이동 
		return new ModelAndView
			("redirect:/qna/detail.do?num="+dto.getNum());
	}
	
	//댓글 저장 요청 처리
	@RequestMapping(value = "/qna/comment_insert", 
			method = RequestMethod.POST)
	public ModelAndView authCommentInsert(HttpServletRequest request) 
	{
		service.saveComment(request);
		return new ModelAndView("redirect:/qna/detail.do?num=");
	}

}
