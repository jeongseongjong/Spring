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
<title>□□□ 나의 JSP 페이지 □□□</title>
<style>
fieldset {
	width: 70%;
	margin: 20px auto;
	border: 1px solid green;
	border-radius: 10px;
}

legend {
	font-weight: bold;
	font-size: 20px;
}

input {
	display:inline-block;
	width:90%;
	padding:8px;
	margin:5px;
	border-radius:20px
}

input:focus, textarea:focus button{
	border:2px solid blue;
	outline:none;
}
</style>
</head>
<body>
	<fieldset>
		<legend>독서록 작성</legend>
		<form:form modelAttribute="readDTO" autocomplete="on">
			<form:input path="rb_seq" class="in-box" placeholder="번호" />
			<br />

			<form:input path="rb_bcode" class="in-box" placeholder="도서코드" />
			<br />

			<form:input path="rb_date" class="in-box" placeholder="작성일" />
			<br />

			<form:input path="rb_stime" class="in-box" placeholder="독서 시작시간" />
			<br />
			
			<form:input path="rb_rtime" class="in-box" placeholder="독서 시간" />
			<br />

			<form:input path="rb_subject" class="in-box" placeholder="한줄소감" />
			<br />
			
			<form:input path="rb_text" class="in-box" placeholder="긴줄소감" />
			<br />
			
			<form:input path="rb_star" class="in-box" placeholder="별점" />
			<br />
			
			<button class="submit">저장</button>
		</form:form>
	</fieldset>
</body>
</html>