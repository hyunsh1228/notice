package com.test.notice;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	// http://localhost:8888/spring02/home.do 에 요청이 왔을 때 
		// /home.do  요청이 왔을때 요청을 처리하게 하는 @RequestMapping 어노테이션
		@RequestMapping("/home.do")
		public String home(HttpServletRequest request) {
			//모델
			List<String> notice=new ArrayList<>();
			notice.add("감기조심");
			notice.add("코로나 조심");
			notice.add("다들 살아 남아요~");
			notice.add("어쩌구...");
			notice.add("저쩌구...");
			
			//모델을 request 에 담는다.
			request.setAttribute("notice", notice);
			
			/*
			 *  "home" 을 리턴해주면
			 *  
			 *  "/WEB-INF/views/"+"home"+".jsp" 와 같은 문자열이 만들어 지고
			 *  
			 *  /WEB-INF/views/home.jsp 페이지로 forward 이동 되어서 
			 *  
			 *  응답된다. 
			 */
			return "home";
		}
	
}
