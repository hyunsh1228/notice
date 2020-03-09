<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- choose when otherwise 를 쓰기 위해 jstl 로딩해준다. --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login.jsp</title>
</head>
<body>
<div class="container">
	<h1>Alert</h1>
	<c:choose>
		<c:when test="${not empty sessionScope.id }">
			<p>
				<strong>${id }</strong>회원님 로그인 되었습니다.
				<a href="${url }">확인</a> <%-- Userscontroller 에서부터 들고 온것 --%>
			</p>
		</c:when>
		<c:otherwise>
			<p>
				아이디 혹은 비밀번호가 틀려요!
				<a href="loginform.do?url=${requestScope.encodedUrl }">다시 로그인 하러 가기</a> <%-- 비밀번호 틀렷으면 url을 잊어먹으므로 다시 로그인 할 때 들고 갈 수 있도록 해준다. --%>
			</p>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>