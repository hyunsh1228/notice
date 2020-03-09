<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/delete.jsp</title>
<jsp:include page="../include/resource.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${isSuccess eq true }">
			<script>
				alert("${num }  번 글을 삭제 했습니다.");
				location.href="${pageContext.request.contextPath }/qna/list.do";
			</script>
		</c:when>
		<c:otherwise>
			<h1>Alert</h1>
			<p class="alert alert-danger">
			글 삭제 실패!
			<a class="alert-link" 
				href="${pageContext.request.contextPath }/qna/detail.do?num=${num }">돌아 가기</a>
			</p>
		</c:otherwise>
	</c:choose>

</div>
</body>
</html>