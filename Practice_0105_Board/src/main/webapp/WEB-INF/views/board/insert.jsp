<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" 
	  content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<style>
	fieldset {
		width:70%;
		margin:20px auto;
		border : 1px solid blue;
		border-radius:10px;
	}
	
	legend{
		fonr-size :20px;
	}
	
	input, textarea {
		display :inline-block;
		width:90%;
		padding:8px;
		margin:5px;
		border-radius: 10px;
	}
	
	input:focus, textarea:focus button{
		border :2px solid red;
		outline: none;
	}
</style>
</head>
<body>
	<fieldset>
		<legend>게시글 작성</legend>
		<form:form modelAttribute="boardDTO" complete="on" class="board-form">
			<input name="b_seq" type=hidden"
					value='<c:out value="${boardDTO.b_seq}" default="0"/>'>
				
			<form:input path="b_date" class="in-box" placeholder="작성일자" /><br/>
			<form:input path="b_auth" class="in-box" placeholder="작성자 id" /><br/>
			<form:input path="b_title" class="in-box" placeholder="제목을 입력하라"/><br/>
			<form:textarea path="b_detail" rows="5"/><br/>
			
			<button>저장</button>
		</form:form>
	</fieldset>
</body>
</html>