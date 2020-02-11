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
<style>
.btn {
	margin: 5px;
	width: 120px;
}

.id-line {
	display: flex;
	justify-content: space-between;
	float: right;
}

.read-box {
	margin-top : 10px;
}

.read-content {
	
	border:none;
	border-bottom:1px solid #ddd;
	background-color:#ccc;
	height:30px;
}
</style>
<body>
	<div class="container">
		<h2>
			<a class="title">독서록 상세보기</a>
		</h2>

		<div class="r-list">
			<div>
				<div class="read-box">도서코드</div>
				<div class="read-content">${readDTO.rb_bcode}</div>
				<div class="read-box">독서시작시간</div>
				<div class="read-content">${readDTO.rb_stime}</div>
				<div class="read-box">작성일</div>
				<div class="read-content">${readDTO.rb_date}</div>
				<div class="read-box">독서시간</div>
				<div class="read-content">${readDTO.rb_rtime}</div>
				<div class="read-box">긴줄소감</div>
				<div class="read-content">${readDTO.rb_text}</div>
			</div>
		</div>
	</div>
</body>
</html>
