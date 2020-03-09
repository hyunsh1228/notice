<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/loginform.jsp</title>
<jsp:include page="../include/resource.jsp"></jsp:include>
<%-- loginform css 로딩 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/loginform.css" />
</head>
<body>
	<form action="${pageContext.request.contextPath }/user/login.do" method="post" class="login-form">
		<h1>Login</h1>
		<%-- 폼 제출할 때 목적지 정보도 같이 보내준다. --%>
		<input type="hidden" name="url" value="${url }" />
		
		<div class="txtb">
			<input type="text" id="id" name="id" value="${savedId }"/>
			<span data-placeholder="Username"></span>
		</div>
		
		<div class="txtb">
			<input type="password" id="pwd" name="pwd" value="${savedPwd }"/>
			<span data-placeholder="Password"></span>
		</div>
		<div class="checkbox">
			<label>
				<input type="checkbox" name="isSave" value="yes"/>Save ID, Password
			</label>
		</div>
		<input type="submit" class="logbtn" value="Login" />
		
		<div class="bottom-text">
			Don't have account? <a href="${pageContext.request.contextPath }/user/signup_form.do">Sign up</a>
		</div>
	</form>
	
	<script type="text/javascript">
		$(".txtb input").on("focus",function(){
			$(this).addClass("focus");
		});
		
		$(".txtb input").on("blur",function(){
			if($(this).val() == "")
			$(this).removeClass("focus");
		});
	</script>
</body>
</html>