package com.test.notice.exception;

import org.springframework.dao.DataAccessException;

public class NoDeliveryException extends DataAccessException{
	
	//생성자의 인자로 예외 메세지를 전달 받아서  (인자가 있는 경우)
	public NoDeliveryException(String msg) {
		//부모 생성자에 전달하면
		super(msg);
		//message 필드에 저장된다.
	}

}
