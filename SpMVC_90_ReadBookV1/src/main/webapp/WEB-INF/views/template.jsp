<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap core CSS -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="${rootPath}/css/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${rootPath}/css/css/heroic-features.css" rel="stylesheet">
<script src="${rootPath}/css/vendor/jquery/jquery.min.js"></script>
<script
	src="${rootPath}/css/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<title>□□□ 나의 JSP 페이지 □□□</title>
<head>

</head>
<style>
.search {
	padding-top: 5px;
}

bod {
	height: 100%;
}

footer {
	margin: 0 auto;
}
</style>
<script>
	$(function() {

		$("#search").keypress(
				function(event) {

					if (event.keyCode == 13) {
						
						let a = $("#search").val()
						
						if($.trim(a) == ""){
							
							alert("문자를 입력하세요")
							return false;
						}
							
						
						alert("입력하신 도서명은 : " + strText)
						document.location.href = "${rootPath}/bookList?search="
								+ strText
					}
				})
	})
</script>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Book Data Service</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse menu-line navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="${rootPath}/">Home <span class="sr-only">(current)</span>
					</a></li>
					<li><a class="nav-link" href="${rootPath}/insert">도서등록</a></li>
					<li>
						<a href="${rootPath}/read/insert" class="nav-link">독서록 등록</a>
					</li>
					<li class="nav-item"><c:choose>
							<c:when test="${memberDTO == null || memberDTO.m_id == null }">
								<a id="login" class="nav-link" href="${rootPath}/member/login">로그인</a>
							</c:when>
							<c:otherwise>
								<div class="test">
									<a id="login" class="nav-link" href="${rootPath}/member/logout">로그아웃</a>
								</div>
							</c:otherwise>
						</c:choose></li>
					<li><input type="text" id="search" name="search"></input></li>
				</ul>
			</div>
		</div>
	</nav>
	<c:choose>
		<c:when test="${HOME == 'BLIST'}">
			<%@ include file="/WEB-INF/views/home.jsp"%>
		</c:when>
		<c:when test="${HOME == 'INSERT'}">
			<%@ include file="/WEB-INF/views/insert.jsp"%>
		</c:when>
		<c:when test="${HOME == 'READ_INSERT'}">
			<%@ include file="/WEB-INF/views/read-insert.jsp"%>
		</c:when>
		<c:when test="${HOME == 'RLIST_DETAIL'}">
			<%@ include file="/WEB-INF/views/read.jsp"%>
		</c:when>
		<c:when test="${HOME == 'RLIST'}">
			<%@ include file="/WEB-INF/views/view.jsp"%>
		</c:when>
	</c:choose>


</body>

<!-- Footer -->
<footer class="py-5 bg-dark">
	<p class="m-0 text-center text-white">Copyright &copy; Your Website
		2019</p>
	<!-- /.container -->
</footer>