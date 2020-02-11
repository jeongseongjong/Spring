<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
</head>
<body>
	<sec:authorize access="isAnonymous()">
		<a href="${rootPath}/login">로그인</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<form method="POST" action="${rootPath}/logout">
			<input type="hidden" 
				name="${_csrf.parameterName}"
				value="${_csrf.token}">
			<button type="submit">로그아웃</button>
		</form>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN')">
		<p>여기는 관리자 페이지</p>		
	</sec:authorize>
	<sec:authorize access="hasRole('USER')">
		<p>여기는 일반 사용자 페이지</p>
	</sec:authorize>
</body>
</html>