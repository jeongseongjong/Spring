<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" 
	  content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
</head>
<body>
 <p><a href="${rootPath}/param/update?id=10">나는 코드가 10번</a>
</body>
</html>