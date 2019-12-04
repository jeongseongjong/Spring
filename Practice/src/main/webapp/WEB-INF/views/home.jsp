<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Practice</title>
</head>
<body>
<h1>Main page</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="userInput">회원가입</a>
<a href="login">로그인</a>
<a href="logout">로그아웃</a>
<a href="menu">목차</a>

</body>
</html>
