package com.test.notice.aspect;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class LoginAspect {
	
	@Around("execution(org.springframework.web.servlet.ModelAndView auth*(..))") //return type ModelAndView 이고 메소드 이름이 auth로 시작하는 메소드 (인자는 0개 이상)
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		//aop 가 적용된 메소드에 전달된 값(인자)을 Object[] 로 얻어오기
		Object[] args=joinPoint.getArgs(); //(..) 의 인자 값이 여러개 전달 될 수 있으므로 Object type으로 받는다.
		//로그인 여부(목적)
		boolean isLogin=false;
		HttpServletRequest request=null;
		for(Object tmp:args) {
			//인자로 전달된 값중에 HttpServletRequest type 을 찾아서
			if(tmp instanceof HttpServletRequest) {//만일 HttpServletRequest type 이면
				//원래 type 으로 casting
				request=(HttpServletRequest)tmp;
				//HttpSession 객체 얻어내기 
				HttpSession session=request.getSession();
				//세션에 "id" 라는 키값으로 저장된게 있는지 확인(로그인 여부)
				if(session.getAttribute("id") != null) {
					isLogin=true;
				}
			}
		}
		//로그인 했는지 여부
		if(isLogin){
			//aop 가 적용된 메소드를 실행하고 리턴되는 값  받아오고
			Object obj=joinPoint.proceed(); // joinPoint => aop가 적용된 바로 그 지점으로  실행순서를 보낸다.
			// 리턴되는 값을 리턴해 주기  (AOP가 적용된 메소드의 return 값을 여기서 해준다.)
			return obj; //로그인 했으면 개입하지 않고 리턴해준다. (여기서는 obj는 ModelAndView 를 가리킨다.)
		}
		/*
		 * 		아래와 같은 주소가 있을 경우
				http://localhost:8080/test/index.jsp
				 
				request.getRequestURI();   //프로젝트경로부터 파일까지의 경로값을 얻어옴 (/test/index.jsp)
				request.getContextPath();  //프로젝트의 경로값만 가져옴(/test)
				request.getRequestURL();   //전체 경로를 가져옴 (http://localhost:8080/test/index.jsp)
				request.getServletPath();  //파일명 (/index.jsp)
		 */
		//원래 가려던 url 정보 읽어오기 
		String url=request.getRequestURI();
		//GET 방식 전송 파라미터를 query string 으로 얻어오기
		String query=request.getQueryString(); //?뒤의 파라미터가 query에 담김, 예를들어 num=1&name=gura
		
		String encodedUrl=null;
		if(query==null) {//전달된 파라미터가 없다면 
			encodedUrl=URLEncoder.encode(url);
		}else {
			encodedUrl=URLEncoder.encode(url+"?"+query);
		}
		//ModelAndView 객체를 생성해서 	
		ModelAndView mView=new ModelAndView();
		//로그인 폼으로 리다일렉트 시키도록 view page 설정
		mView.setViewName
		("redirect:/user/loginform.do?url="+encodedUrl);
		
		//여기서 생성한 객체를 리턴해 준다. 
		return mView;
	}
	
	@Around("execution(java.util.Map auth*(..))") //return type Map 이고 메소드 이름이 auth로 시작하는 메소드 (인자는 0개 이상)
	public Object loginCheckAjax(ProceedingJoinPoint joinPoint) throws Throwable {
		//aop 가 적용된 메소드에 전달된 값(인자)을 Object[] 로 얻어오기
		Object[] args=joinPoint.getArgs(); //(..) 의 인자 값이 여러개 전달 될 수 있으므로 Object type으로 받는다.
		//로그인 여부(목적)
		boolean isLogin=false;
		HttpServletRequest request=null;
		for(Object tmp:args) {
			//인자로 전달된 값중에 HttpServletRequest type 을 찾아서
			if(tmp instanceof HttpServletRequest) {//만일 HttpServletRequest type 이면
				//원래 type 으로 casting
				request=(HttpServletRequest)tmp;
				//HttpSession 객체 얻어내기 
				HttpSession session=request.getSession();
				//세션에 "id" 라는 키값으로 저장된게 있는지 확인(로그인 여부)
				if(session.getAttribute("id") != null) {
					isLogin=true;
				}
			}
		}
		//로그인 했는지 여부
		if(isLogin){
			//aop 가 적용된 메소드를 실행하고 리턴되는 값  받아오고
			Object obj=joinPoint.proceed(); // joinPoint => aop가 적용된 바로 그 지점으로  실행순서를 보낸다.
			// 리턴되는 값을 리턴해 주기  (AOP가 적용된 메소드의 return 값을 여기서 해준다.)
			return obj; //로그인 했으면 개입하지 않고 리턴해준다. (여기서는 obj는 ModelAndView 를 가리킨다.)
		}
		// 로그인을 하지 않았으면
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", false);
		return map; // {"isSuccess":false}
	}
}
