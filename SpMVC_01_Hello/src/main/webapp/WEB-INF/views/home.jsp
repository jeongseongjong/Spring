<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h3>나의 Spring Home</h3>
	<p>서버시간 : ${serverTime }.</p>
	<h3>학생정보</h3>
	<form action="my" method="POST">
	<p>이름 : ${st_name }
	<input name="strName"></br>
	<p>학과 : ${st_dept }
	<input name="strDept"></br>
	<p>학년 : ${st_grade }
		<input name="strGrade">
		<button>전송</button>
	</form>
</body>
</html>
