<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
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
		document.location.href="${rootPath}/memo/insert";
	})
})
</script>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

html, body {
	height: 100%;
}

body {
	width: 978px;
	display: flex;
	flex-flow: column wrap;
	margin: 0 auto;
}

header {
	background: #ddd;
	text-align: center;
	padding: 0.8rem;
}

footer {
	flex: 0 0 auto;
	background-color: green;
	color: white;
	text-align: center;
	padding: 0.2rem;
}

section#main-body {
	/* 
		flex : 1 0 auto 
		화면 가득히 box를 채우기 위한 설정
	*/
	flex-grow: 1; /* 최대크기로 */
	flex-shrink: 0; /* 최소화 */
	flex-basis: auto;
	background-color: #ddd;
	display: flex;
}

section#main-body article {
	flex: 5;
}

section#main-body aside {
	flex: 1;
	border: 1px solid blue;
	background-color: white;
	padding: 16px;
	border-radius: 10px;
}

section#main-body ul {
	list-style: none;
	margin-left: 16px;
}

section#main-body li a {
	/* 
		a tag에 width, height를 설정하기 위해서는
		display를 block 또는 inline-block으로 설정 해야한다.
	 */
	display: inline-block;
	width: 100px;
	border-bottom: 1px solid blue;
	padding: 10px 16px;
	text-decoration: none;
}

section#main-body li a:hover {
	background-color: #ccc;
}

article.list {
	border: 1px solid red;
	height: 80%;
	overflow:auto;
}
div.b-box{
	display:flex;
	justify-content:center;
	align-items:center;
	padding:0.8rem;
}
div.b-box button {
	background-color:orange;
	color:blue;
	font-weight:bold;
	padding: 8px 16px;
	border : 0px;
	border-radius:5px;
}

div.b-box button:hover{
	background-color:#ddd;
}

div.s-box {
	width:100%;
	border: 1px solid blue;
	margin-bottom:5px;
}
div.s-box input {
	display:block;
	width:90%;
	margin:10px auto;	
}
nav ul{
	list-style:none;
	background-color:blue;
	color:white;
	display:flex;
}

nav li:nth-child(2) {
	margin-left:auto;
}

nav li:nth-child(4) {
	margin-left:auto;
}

nav ul a {
	text-decoration:none;
	display:inline-block;
	color:inherit;
	padding:8px;
	margin:5px;
}
</style>
</head>
<body>
	<header>
		<h3>심플 메모장</h3>
	</header>
	<nav>
		<ul>
			<li><a href="${rootPath}/">홈으로</a>
			<li><a href="${rootPath}/">메뉴1</a>
			<li><a href="${rootPath}/">메뉴2</a>
			
			<c:if test="${userDTO == null}">
			<li><a href="${rootPath}/member/login">로그인</a>
			<li><a href="${rootPath}/user/join">회원가입</a>
			</c:if>
			
			<c:if test="${userDTO != null}">
			<li><a href="${rootPath}/member/logout">로그아웃</a>
			<li><a href="${rootPath}/member/mypage">${userDTO.u_name}</a>
			</c:if>
		</ul>
	</nav>
	<section id="main-body">
		<article>

			<div class="s-box">
				<form>
					<input type="text" name="search">
				</form>
			</div>
			<article class="list">
			<%@ include file="/WEB-INF/views/list.jsp" %>
			</article>
			<div class="b-box">
				<button id="btn-insert">메모작성</button>

			</div>
		</article>

		<aside>
			<ul>
				<li><a href="#">오늘할일</a></li>
				<li><a href="#">약속</a></li>
				<li><a href="#">중요메모</a></li>
				<li><a href="${rootPath}/html/hello.html">hello</a></li>
				<li><a href="${rootPath}/images/readme.txt">readme</a></li>
			</ul>
		</aside>
	</section>
	<footer>
		<address>CopyRight &copy; jjong9950@naver.com</address>
	</footer>
</body>
</html>