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
<script src="${rootPath}/js/jquery-3.4.1.js"></script>
<script>
	$(function(){
		$("#btn-insert").click(function(){
			document.location.href="${rootPath}/board/insert";
		})
	})
</script>
<style>

	h2{
		text-align: center;
	}

	.login {
		display:flex;
		justify-content: flex-end;	
		text-decoration: none;
	}
	
	table{
		width:100%;
		border-top:1px solid red;
		border-collapse: collapse;
	}
	.title{
		font-size :20px; 
		text-align:center;
	}
	
	.detail { 
		font-size:12px;
	}
	th{
		border-bottom:1px solid red;
	}
	
	.button{
		padding-top:10px;
		width:100px;
		align-content: center;
		
	}
</style>
</head>
<body>
	<h2>게시판~~~~</h2>
	<header class="login">
		<a href="#">로그인 &nbsp;</a>
		<a href="#">회원가입</a>
	</header>
	<table class="list">
		<tr class= "title">
			<th>SEQ</th>
			<th>제목</th>
			<th>날짜</th>
		</tr>
		<tr class=detail>
			<th>1번</th>
			<th>오늘은 5일</th>
			<th>2020-01-15</th>
		</tr>
	</table>
	<section class="button">
		<button type="button">수정</button>
		<button type="button">삭제</button>
		<button type="button" id="btn-insert">글쓰기</button>
	</section>
</body>
</html>


















