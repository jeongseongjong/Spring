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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>
.btn {
	margin: 5px;
	width: 120px;
}

.id-line {
	display:flex;
	justify-content: space-between;
}
</style>
<body>
	<div class="container">
		<h2>
			<a href="${rootPath}/" class="title">도서 목록 리스트</a>
		</h2>
		<div>
			<div class="id-line">
			<a href="${rootPath}/read/insert" class="btn btn-outline-dark">독서록 등록</a>
				<div class="test">${memberDTO.m_id}님안녕하세요</div>
				<a id="login" href="${rootPath}/member/logout"><i
					class="fas fa-sign-out-alt fa-2x"></i></a> 
			</div>
		</div>
		<table border="1" class="r-list table-striped table-hover">
			<tr>
				<th>도서코드</th>
				<td>${readDTO.rb_bcode}</td>
				<th>독서시작시간</th>
				<td>${readDTO.rb_stime}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${readDTO.rb_date}</td>
				<th>독서시간</th>
				<td>${readDTO.rb_rtime}</td>
			</tr>
			<tr>
				<th>긴줄소감</th>
				<td colspan="3">${readDTO.rb_text}</td>
			</tr>
		</table>
	</div>
</body>
</html>
