package com.test.notice.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.notice.user.dto.UserDto;



public interface UserService {
	public Map<String, Object> isExistId(String inputId);
	public void addUser(UserDto dto);
	public void validUser(UserDto dto, HttpSession session, ModelAndView mView); //비즈니스 로직에 필요한 것을 생각해서 명시한다.
	public void showInfo(String id, ModelAndView mView); // id를 전달하면 mView 에 정보를 담아주는 로직을 처리 할 예정
	public void updatePassword(UserDto dto, ModelAndView mView); //dto에 id, pwd, newpwd 담아서 전달 
	public void updateUser(UserDto dto);
	public void deleteUser(String id);
}
