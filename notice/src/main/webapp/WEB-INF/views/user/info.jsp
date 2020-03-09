<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/info.jsp</title>
<jsp:include page="../include/resource.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<h1>개인 정보 페이지</h1>
	<table class="table table-bordered table-condensed">
		<tr>
			<th>아이디</th>
			<td>${dto.id }</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><a class="btn btn-warning btn-xs" href="pwd_updateform.do">수정하기</a></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${dto.email }</td>
		</tr>
		<tr>
			<th>가입일</th>
			<td>${dto.regdate }</td>
		</tr>
	</table>
	<a class="btn btn-primary" href="updateform.do">개인 정보 수정하기</a>
	<a class="btn btn-danger" href="javascript:deleteConfirm()">회원 탈퇴</a>
</div>
<script>
	function deleteConfirm(){
		var isDelete=confirm("${id} 님 탈퇴 하시겠습니까?");
		if(isDelete){
			location.href="delete.do";
		}
	}
</script>
</body>
</html>