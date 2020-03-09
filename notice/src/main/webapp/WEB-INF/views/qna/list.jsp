<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/list.jsp</title>
<%-- include 상대경로 주의하기! --%>
<jsp:include page="../include/resource.jsp"></jsp:include>
</head>
<body>
<%-- jsp:param => 파라미터로 카테고리 전달하는 것 (active 시키기 위해) include 내부에 주석 X--%>
<jsp:include page="../include/navbar.jsp">
	<jsp:param value="qna" name="category"/>
</jsp:include>
<div class="container">
	<h1>QnA</h1>
		<c:if test="{not empty keyword }">
			<p class="breadcrumb">
				<strong>${keyword }</strong> 라는 키워드로
				<strong>${totalRow }</strong> 개의 글이 검색되었습니다.
			</p>
		</c:if>
	<table class="table table-bordered table-condensed">
		<%-- 칼럼의 폭을 임의로 조절할 수 있다. 합이 12이여야 한다(12등분이므로) --%>
		<colgroup> 
			<col class="col-xs-1"/>
			<col class="col-xs-2"/>
			<col class="col-xs-5"/>
			<col class="col-xs-1"/>
			<col class="col-xs-3"/>
		</colgroup>
		<thead>
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>조회수</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="tmp" items="${requestScope.list }"> <%-- request에 담긴 list --%>
			<tr>
				<td>${tmp.num }</td>
				<td>${tmp.writer }</td>
				<td>
					<a href="detail.do?num=${tmp.num }&pageNum=${pageNum}">
						${tmp.title } <%-- 글을 눌렀을 때 자세히 보기 되도록 링크를 걸고 num 이라는 파라미터에 글번호를 가져간다. --%>
					</a>
				</td>
				<td>${tmp.viewCount }</td>
				<td>${tmp.regdate }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<a style="float:right" class="btn btn-info" href="insertform.do">새글 작성</a> <!-- cafe/private/* 로그인 안하면  사용 못하게 필터링! -->
	
	<div class="page-display">
		<ul class="pagination pagination-sm" style="padding-left: 36%;">
			<c:choose>
				<c:when test="${startPageNum ne 1 }"> <%-- startPageNum != 1 --%>
					<li>
						<a href="list.do?pageNum=${requestScope.startPageNum-1 }&condition=${condition}&keyword=${encodedKeyword}">&laquo;</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="disabled">
						<a href="javascript:">&laquo;</a> <%-- : 아무것도 적지 않으면  동작하지 않는 링크가 된다. --%>
					</li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }"> 	
				<c:choose>
					<c:when test="${i eq requestScope.pageNum }">
						<li class="active">
							<a href="list.do?pageNum=${i }&condition=${condition}&keyword=${encodedKeyword}">${i }</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="list.do?pageNum=${i }&condition=${condition}&keyword=${encodedKeyword}">${i }</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${endPageNum lt totalPageCount }">
					<li>
						<a href="list.do?pageNum=${endPageNum+1 }&condition=${condition}&keyword=${encodedKeyword}">&raquo;</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="disabled">
						<a href="javascript:">&raquo;</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- GET 방식으로 제출되므로 file/list.do?condition=x&keyword=xx 이런 형태로 주소창에 출력된다.-->
	<form action="list.do" method="get">
		<label for="condition">검색조건</label>
		<select name="condition" id="condition">
			<option value="titlecontent" <c:if test="${condition eq 'titlename' }">selected</c:if>>제목+내용</option> <!-- selected 문자열을 조건문으로 찍어준다. -->
			<option value="title" <c:if test="${condition eq 'title' }">selected</c:if>>제목</option> <!-- selected value 값을 적어주면 검색을 눌렀을 때 고정된다. -->
			<option value="writer" <c:if test="${condition eq 'writer' }">selected</c:if>>작성자</option>
		</select>
		<input type="text" name="keyword" id="keyword" placeholder="검색어 입력..." value="${keyword }"/> <!-- EL은 아무것도 들어있지 않으면 null이 아니라 빈문자열을 출력한다 -->
		<button type="submit">검색</button>
	</form>
</div>
</body>
</html>