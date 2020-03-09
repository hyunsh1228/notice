package com.test.notice.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.test.notice.user.dao.UserDao;
import com.test.notice.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;
	@Override
	public Map<String, Object> isExistId(String inputId) {
		boolean isExist=dao.isExist(inputId);
		Map<String, Object> map=new HashMap<>();
		map.put("isExist",isExist);
		return map;
	}

	@Override
	public void addUser(UserDto dto) {
		//비밀번호를 암호화 한다.
		String encodedPwd=new BCryptPasswordEncoder().encode(dto.getPwd());
		//암호화된 비밀번호를 UsersDto 에 다시 넣어준.
		dto.setPwd(encodedPwd);
		//UsersDao 객체를 이용해서 DB에 저장하기
		dao.insert(dto);
	}

	@Override
	public void validUser(UserDto dto, HttpSession session, ModelAndView mView) {
		//아이디 비밀번호가 유효한지 여부
		boolean isValid=false;
		//아이디를 이용해서 저장된 비밀번호를 읽어온다.
		String pwdHash=dao.getPwdHash(dto.getId());
		if(pwdHash != null) { //1. 아이디가 존재하고(비밀번호가 존재하고)
							  //2. 입력한 비밀번호와 일치 하다면 로그인 성공
			isValid=BCrypt.checkpw(dto.getPwd(), pwdHash); //true or false
		}
		if(isValid) {
			//로그인 처리를 한다.
			session.setAttribute("id", dto.getId());
		}
	}

	@Override
	public void showInfo(String id, ModelAndView mView) {
		UserDto dto=dao.getData(id);
		mView.addObject("dto",dto);
	}

	@Override
	public void updatePassword(UserDto dto, ModelAndView mView) {
		//1. 예전 비밀번호가 맞는 정보인지 확인
		String pwdHash=dao.getData(dto.getId()).getPwd();
		boolean isValid=BCrypt.checkpw(dto.getPwd(), pwdHash);
		//2. 만일 맞다면 새로 비밀번호를 암호화 해서 저장하기
		if(isValid) {
			//새 비밀번호를 암호화 해서 dto 에 담고
			String encodedPwd=new BCryptPasswordEncoder().encode(dto.getNewPwd());
			dto.setPwd(encodedPwd);
			//DB 에 수정 반영하기
			dao.updatePwd(dto);
			mView.addObject("isSuccess", true);
		}else {
			
		}
		
	}

	@Override
	public void updateUser(UserDto dto) {
		dao.updateUser(dto);
	}

	@Override
	public void deleteUser(String id) {
		dao.delete(id);
	}

}
