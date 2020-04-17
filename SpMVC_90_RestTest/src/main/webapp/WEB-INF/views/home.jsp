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
<body>
	<div>
	<a href="${rootPath}/searchNaver"><button>도서등록</button></a>
		<div>
			<div>code :</div>
			<div>name :</div>
			<div>auther :</div>
			<div>comp :</div>
			<div>year :</div>
			<div>iprice :</div>
		</div>
		<c:forEach items="${restList}" var="vo">
			<div>${vo.b_code}</div>
			<div>${vo.b_name}</div>
			<div>${vo.b_auther}</div>
			<div>${vo.b_comp}</div>
			<div>${vo.b_year}</div>
			<div>${vo.b_iprice}</div>
		</c:forEach>
	</div>
</body>
</html>