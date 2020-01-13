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
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="${rootPath}/css/main.css">
<style>
.container {
	width: 100%;
	text-align: center;
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

.r-list {
	width: 100%;
	display: flex;
	justify-content: center;
	border: none;
}
</style>
<script>
	$(function() {

		$("#delete").click(function() {
			confirm("삭제하시겠습니까?")
		})
		
		$("#view").click(function(){
			confirm("내용을 보시겠습니까?")
		})
	})
</script>
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
				<div class="test">${memberDTO.m_id}님안녕하세요</div>
				<a id="login" href="${rootPath}/member/logout"><i
					class="fas fa-sign-out-alt fa-2x"></i></a>
			</c:otherwise>
		</c:choose>
		<table border="1" class="r-list">
			<tr>
				<th>번호</th>
				<th>도서코드</th>
				<th>작성일</th>
				<th>독서시작시간</th>
				<th>독서시간</th>
				<th>한줄소감</th>
				<th>별점</th>
				<th>비고</th>
				<th>자세히보기</th>
			</tr>
			<c:forEach items="${RLIST}" var="vo">
				<tr class="r-row" data-id="${vo.rb_bcode}">
					<td>${vo.rb_seq}</td>
					<td>${vo.rb_bcode}</td>
					<td>${vo.rb_date}</td>
					<td>${vo.rb_stime}</td>
					<td>${vo.rb_rtime}</td>
					<td>${vo.rb_subject}</td>
					<td>${vo.rb_star}</td>
					<td><a href="${rootPath}/read/update?id=${vo.rb_seq}">수정</a>
					<div id="delete"><a href="${rootPath}/read/delete?id=${vo.rb_seq}">삭제</a></div></td>
					<td><div id="view"><a href="${rootPath}/read/view?id=${vo.rb_seq}">상세보기</a></div></tr>
			</c:forEach>
		</table>
		<div>
			<a href="${rootPath}/read/insert">독서록 등록</a>
		</div>
	</div>
</body>
</html>
