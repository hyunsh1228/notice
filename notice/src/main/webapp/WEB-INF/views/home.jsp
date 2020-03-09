<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
<!-- views/include/resource.jsp -->
<jsp:include page="include/resource.jsp"/><!-- 파라미터 전달할 게 없으면 바로 닫아주면 된다. -->
</head>
<body>
<jsp:include page="include/navbar.jsp"/>
<div class="container">
	<h1>공지사항</h1>
	<ul>
		<li><a href="notice/list.do">공지사항 보기</a></li>
	</ul>
</div>
</body>
</html>