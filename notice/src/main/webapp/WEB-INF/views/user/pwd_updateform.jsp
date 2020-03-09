<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/private/pwd_updateform.jsp</title>
<jsp:include page="../include/resource.jsp"/>
</head>
<body>
<div class="container">
	<h1>비밀번호 수정 페이지</h1>
	<form action="pwd_update.do" method="post">
		<div class="form-group">
			<label for="pwd">기존 비밀번호</label>
			<input class="form-control" type="password" name="pwd" id="pwd" />
		</div>
		<div class="form-group">
			<label for="newPwd">새 비밀번호</label>
			<input class="form-control" type="password" name="newPwd" id="newPwd" />
		</div>
		<div class="form-group">
			<label for="newPwd2">새 비밀번호 확인</label>
			<input class="form-control" type="password" name="newPwd2" id="newPwd2" />
		</div>
		<button class="btn btn-info" type="submit">수정확인</button>
	</form>
</div>
<script>
	//폼에 submit 이벤트가 일어났을 때 실행 할 함수 등록 (jquery 이용해서 맞게 썼는지 확인) 
	$("form").on("submit", function(){
		//새 비밀번호 2개가 일치하는지 확인해야 한다.
		var pwd=$("#newPwd").val();
		var pwd2=$("#newPwd2").val();
		if(pwd != pwd2){
			alert("비밀번호를 확인 하세요."); //알림 띄우기
			$("#newPwd").focus(); //포커스 주기
			return false; //폼 전송 막기
		}
	});
</script>
</body>
</html>