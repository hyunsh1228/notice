package com.test.notice.user.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.notice.user.dto.UserDto;
import com.test.notice.user.service.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	@RequestMapping("/user/signup_form")
	public String signup_form() {
		return "user/signup_form";
	}
	@ResponseBody
	@RequestMapping("/user/checkid")
	public Map<String, Object> checkid(@RequestParam String inputId){
		Map<String, Object> map=service.isExistId(inputId); ////Map에서 {"isExist":true} or {"isExist":false} 이렇게 응답 됨.
		return map; // ajax 이므로 Map 으로 리턴해준다.
	}
	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("dto") UserDto dto, ModelAndView mView) {
		service.addUser(dto);
		mView.setViewName("user/insert");
		return mView;
	}
	//로그인 폼 요청 처리
	@RequestMapping("/user/loginform")
	public String loginForm(HttpServletRequest request) { //request가 필요하므로 인자에 선언해준다.
		// "url" 이라는 파라미터가 넘어오는지 읽어와 본다.  
		String url=request.getParameter("url");
		if(url==null){//만일 없으면 
			//로그인 성공후에 index 페이지로 보낼수 있도록 구성한다. 
			url=request.getContextPath()+"/home.do";
		}
		//아이디, 비밀번호가 쿠키에 저장되었는지 확인해서 저장 되었으면 폼에 출력한다.
		Cookie[] cookies=request.getCookies();
		//저장된 아이디와 비밀번호를 담을 변수 선언하고 초기값으로 빈 문자열 대입
		String savedId="";
		String savedPwd="";
		if(cookies != null){
			for(Cookie tmp:cookies){
				//쿠키의 키값이 "savedId" 라면 (내가 원하는 쿠기를 빼내온다)
				if(tmp.getName().equals("savedId")){
					//쿠키로 저장된 값(value)을 읽어온다.
					savedId=tmp.getValue();
				}else if(tmp.getName().equals("savedPwd")){
					savedPwd=tmp.getValue();
				}
			}
		}
		//view page 에서 필요한 정보 넘겨주기
		request.setAttribute("url", url);
		request.setAttribute("savedId", savedId);
		request.setAttribute("savedPwd", savedPwd);
		return "user/loginform"; // 포워드 이동이므로 request로 정보를 담아놓을 수 있
	}
	//로그인 요청 처리
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute UserDto dto, ModelAndView mView, //1. HttpSession session(session이 필요하다면 선언해주면된다.) 길어져서 2번으로 얻음
			HttpServletRequest request,
			HttpServletResponse response) {
		//목적지 정보
		String url=request.getParameter("url");
		if(url==null){
			url=request.getContextPath()+"/home.do";
		}
		//목적지 정보를 미리 인코딩 해 놓는다.
		String encodedUrl=URLEncoder.encode(url);
		// view page 에 전달하기 
		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);
		
		//아이디 비밀번호 저장 체크박스를 체크 했는지 읽어와 본다.
		String isSave=request.getParameter("isSave");	
		//아이디, 비밀번호를 쿠키에 저장
		Cookie idCook=new Cookie("savedId", dto.getId());
		Cookie pwdCook=new Cookie("savedPwd", dto.getPwd());
		if(isSave != null){ // null 이 아니면 체크 한 것이다.
			//한달 동안 저장하기
			idCook.setMaxAge(60*60*24*30);
			pwdCook.setMaxAge(60*60*24*30);
		}else{
			//쿠키 지우기 
			idCook.setMaxAge(0);
			pwdCook.setMaxAge(0);
		}
		response.addCookie(idCook);
		response.addCookie(pwdCook);
				
		service.validUser(dto, request.getSession(), mView);
		
		mView.setViewName("user/login"); //쿠키까지 같이 응답된다.
		return mView;
	}
	//로그아웃 처리
	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/home.do"; //contextPath X
	}
	//개인 정보 보기 요청 처리
	@RequestMapping("/user/info")
	public ModelAndView authInfo(HttpServletRequest request, ModelAndView mView) { //어떤 요청이 로그인 했을 때만 처리 할려면 앞에 auth를 붙이고 aop 처리를 한다. auth*(..) 
		//로그인된 아이디 읽어오기
		String id=(String)request.getSession().getAttribute("id");
		//UsersService 객체를 이용해서 개인정보를 ModelAndView 객체에 담기도록 한다.
		service.showInfo(id, mView);
		//view page 정보를 담고
		mView.setViewName("user/info");
		return mView; //ModelAndView 객체를 리턴해주기
	}
	//비밀번호 수정하기 폼 요청 처리
	@RequestMapping("/user/pwd_updateform")
	public ModelAndView authPwdForm(HttpServletRequest request, ModelAndView mView) { //로그인 했을 때만 작동해야 되므로 return type과 메소드명을 맞춰준다
		mView.setViewName("user/pwd_updateform");
		return mView;
	}
	//비밀번호 수정 반영 요청 처리
	@RequestMapping("/user/pwd_update")
	public ModelAndView authPwdUpdate(HttpServletRequest request, ModelAndView mView) {
		//기존 비밀번호
		String pwd=request.getParameter("pwd");
		//새 비밀번호
		String newPwd=request.getParameter("newPwd");
		//로그인된 아이디
		String id=(String)request.getSession().getAttribute("id");
		//위의 3가지 정보를 UsersDto 객체에 담고
		UserDto dto=new UserDto();
		dto.setPwd(pwd);
		dto.setNewPwd(newPwd);
		dto.setId(id);
		//서비스에 전달
		service.updatePassword(dto, mView);
		
		mView.setViewName("user/pwd_update");
		return mView;
	}
	//회원정보 수정폼 요청처리
	@RequestMapping("/user/updateform")
	public ModelAndView anthUdpdateform(HttpServletRequest request, ModelAndView mView) {
		//세션 영역에서 로그인된 id 를 읽어와서 
		String id=(String)request.getSession().getAttribute("id"); //세션의 참조값.session 영역에서 id 읽어오기
		//서비스 메소드를 호출해서 ModelAndView 객체에 회원정보가 담기게 하고
		service.showInfo(id, mView);
		//view page 설정한 다음
		mView.setViewName("user/updateform");
		return mView; //리턴해준다.
	}
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ModelAndView authUpdate(@ModelAttribute UserDto dto, HttpServletRequest request) {
		//서비스를 이용해서 수정 반영하고
		service.updateUser(dto);
		//개인정보 보기로 다시 리다일렉트 이동 시킨다.
		return new ModelAndView("redirect:/user/info.do");//생성자의 인자에 View Page의 정보를 전달해도 setViewName 메소드를 작성하는것과 동일하다.
	}
	@RequestMapping("/user/delete")
	public ModelAndView authDelete(HttpServletRequest request, ModelAndView mView) {
	String id=(String)request.getSession().getAttribute("id");
	//서비스를 이용해서 해당 회원 정보 삭제
	service.deleteUser(id);
	//로그 아웃 처리(id 를 전달해서 회원 정보가 삭제되었을시 로그아웃 되야한다.)
	HttpSession session=request.getSession();
	session.invalidate();
	
	mView.addObject("id", id);
	mView.setViewName("user/delete");
	return mView;
	}
}
