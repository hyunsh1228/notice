package com.test.notice.user.dao;

import com.test.notice.user.dto.UserDto;

public interface UserDao {
	public boolean isExist(String inputId);
	public void insert(UserDto dto);
	//입력한 아이디를 이용해서 암호화 된 비밀번호를 가져와야한다.(가입되지 않은 아이디면 null = 로그인 실패, 가입된 아이디여도 입력한 비밀번호와 일치해야된다.)
	public String getPwdHash(String inputId);
	public UserDto getData(String id);
	public void updatePwd(UserDto dto);
	public void updateUser(UserDto dto);
	public void delete(String id);
}
