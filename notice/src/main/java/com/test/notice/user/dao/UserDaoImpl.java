package com.test.notice.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.notice.user.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private SqlSession session;

	@Override
	public boolean isExist(String inputId) {
		String id=session.selectOne("user.isExist", inputId); //select 된 row 1개(selectOne)
		//만일 select 된 결과가 null 이면 존재하지 않는 아이디이다.
		if(id==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void insert(UserDto dto) {
		session.insert("user.insert", dto);
	}

	@Override
	public String getPwdHash(String inputId) {
		String savedPwd=session.selectOne("user.getPwdHash", inputId);
		//select 된 비밀번호를 리턴해준다.
		return savedPwd;
	}

	@Override
	public UserDto getData(String id) {
		return session.selectOne("user.getData", id);
	}

	@Override
	public void updatePwd(UserDto dto) {
		session.update("user.updatePwd", dto);
	}

	@Override
	public void updateUser(UserDto dto) {
		session.update("user.updateUser", dto);
	}

	@Override
	public void delete(String id) {
		session.delete("user.delete", id);	
	}

}
