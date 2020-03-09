<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../include/resource.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${isSuccess}">
			<script>
				alert("글이 수정되었습니다.")
				location.href="${pageContext.request.contextPath }/notice/detail.do?num=${num}&pageNum=${pageNum}";
			</script>
		</c:when>
		<c:otherwise>
			<h1>Alert</h1>
			<p class="alert alert-danger">
				글 수정 실패!
				<a class="alert-link" href="${pageContext.request.contextPath }/notice/updateform.do?num=${num}">다시시도</a>
			</p>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>