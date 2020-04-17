<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf"%>
<script>
	$(function() {
		$("a.logout").click(function() {
			$("#logout").submit()
		})
	})
</script>
</head>
<body>
	<header class="jumbotron">
		<h3>MY SHOP</h3>
	</header>
	<nav class="navbar navbar-expand-sm bg-light">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="${rootPath}">Home</a>
			</li>

			<!-- 
			Anonymous는 익명인데 현재 로그인을 하지않아 익명인 상태이기 때문에
			로그인 이라는 글자가 화면에 띄워져야 한다.
		 -->
			<sec:authorize access="isAnonymous()">
				<li class="nav-item"><a class="nav-link"
					href="${rootPath}/auth/login">로그인</a></li>
			</sec:authorize>

			<!-- 
				Authenticated는 인증인데 현재 로그인이 되어 인증되어 있기때문에
				로그아웃이라는 글자가 화면에 띄워져야 한다. 
			-->
			<sec:authorize access="isAuthenticated()">
				<form id="logout" method="POST" action="${rootPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}">
					<li class="nav-item"><a class="nav-link logout" href="#">로그아웃</a></li>
				</form>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN')">
				<li class="nav-item"><a class="nav-link"
					href="${rootPath}/admin/">관리자</a></li>
			</sec:authorize>
		</ul>
	</nav>
</body>
</html>