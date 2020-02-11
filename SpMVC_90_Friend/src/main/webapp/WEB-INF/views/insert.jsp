<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>□□□ 나의 JSP 페이지 □□□</title>
</head>
<body>
	<div>
		<form:form modelAttribute="friendVO" autocomplete="on" id="friendVO" >
		
		<br/>
		<div>이름</div>
		<form:input path="f_name" class="in-box" placeholder="이름"/>
		<br/>
		<div>전화번호</div>
		<form:input path="f_tel" class="in-box" placeholder="전화번호"/>
		<br/>
		<div>주소</div>
		<form:input path="f_addr" class="in-box" placeholder="주소"/>
		<br/>
		<div>취미</div>
		<form:input path="f_hobby" class="in-box" placeholder="취미"/>
		<br/>
		<div>관계</div>
		<form:input path="f_relation" class="in-box" placeholder="관계"/>
		<br/>
		<button class="submit">저장</button>
		<button onclick = "location.href = 'fList'" >취소</button>
		</form:form>
	</div>
</body>
</html>