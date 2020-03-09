<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/insert.jsp</title>
<jsp:include page="../include/resource.jsp"/>
</head>
<body>
<div class="container">{
	<c:choose>
		<c:when test="${isSuccess }">
			<script>
				alert("저장 하였습니다.");
				location.href="${pageContext.request.contextPath }/notice/list.do";
			</script>
		</c:when>
		<c:otherwise>
			<p class="alert alert-danger">
				글 정보 저장 실패!
				<a class="alert-danger" href="insertform.do">다시 작성 하기</a>
			</p>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>