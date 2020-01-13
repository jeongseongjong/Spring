<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="${rootPath}/css/main.css">
<style>
.container {
	width: 100%; text-align : center;
	display: flex;
	flex-direction: column;
	text-align: center;
}

h2 {
	width: 67%;
	background-color: #ccc;
	text-align: center;
	color: white;
	font-size: 40px;
	margin: 0 auto;
}

title {
	text-decoration: none;
	color: white;
}

#search-form {
	margin-top: 5px;
	margin-bottom: 5px;
}

.b-list {
	width: 100%;
	display: flex;
	justify-content: center;
	border: none;
}

.search {
	vertical-align: left;
}
</style>
<script>
	$(function() {

		$("#delete").click(function() {
			alert("삭제하시겠습니까?")
		})
	})
</script>

</head>
<body>
	<div class="container">
		<h2>
			<a href="${rootPath}/" class="title">도서 목록 리스트</a>
		</h2>
		<c:choose>
			<c:when test="${memberDTO == null || memberDTO.m_id == null }">
				<a id="login" href="${rootPath}/member/login"><i
					class="fas fa-sign-in-alt fa-2x"></i></a>
			</c:when>
			<c:otherwise>
				<div class="test">${memberDTO.m_id}님 안녕하세요</div>
				<a id="login" href="${rootPath}/member/logout"><i
					class="fas fa-sign-out-alt fa-2x"></i></a>
			</c:otherwise>
		</c:choose>
		<div>
			<form action="${rootPath}/bookList">
				<input type="text" class="search" name="search">
			</form>
		</div>
		<table border="1" class="b-list">
			<tr>
				<th>도서코드</th>
				<th>도서명</th>
				<th>저자</th>
				<th>출판사</th>
				<th>출판년도</th>
				<th>판매가격</th>
				<th>비고</th>
			</tr>
			<c:forEach items="${BLIST}" var="vo">
				<tr class="b-row" data-id="${vo.b_code}">
					<td>${vo.b_code}</td>
					<td>${vo.b_name}</td>
					<td>${vo.b_auther}</td>
					<td>${vo.b_comp}</td>
					<td>${vo.b_year}</td>
					<td>${vo.b_iprice}</td>
					<td><a href="${rootPath}/update?id=${vo.b_code}">수정</a>
						<div id="delete">
							<a href="${rootPath}/delete?id=${vo.b_code}">삭제</a>
						</div></td>
				</tr>
			</c:forEach>
		</table>
		<div>
			<a href="${rootPath}/insert">도서등록</a>
			<a href="${rootPath}/read/rList">독서록 입력</a>
		</div>
	</div>
</body>
</html>