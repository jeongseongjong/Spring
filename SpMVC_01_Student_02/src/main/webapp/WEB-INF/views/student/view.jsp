<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
<h2>학생정보 보기</h2>
<p>학번: ${dto.st_num }</p>
<p>이름:${dto.st_name }</p>
<p>학과:${dto.st_dept }</p>
<p>학년:${dto.st_grade }</p>
<p>전화번호:${dto.st_tel }</p>

</form>
</body>
</html>