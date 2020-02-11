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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<style>

.insert-box {
	display:grid;
	margin-top:20px;
	margin-bottom:20px;
}

#booksDTO {

	width:100%;
	display:contents;
}

.submit {
	width : 100px;
}

</style>
<body>
	<div class="container">
		<section class="insert-box container">
			<h3>도서 등록</h3>
			<form:form modelAttribute="booksDTO" autocomplete="on" id="booksDTO"
				data-id="${b_code}">
					<form:input path="b_code" class="in-box code" placeholder="도서코드" />
					<br />

					<form:input path="b_name" class="in-box" placeholder="도서명" />
					<br />

					<form:input path="b_auther" class="in-box" placeholder="저자" />
					<br />

					<form:input path="b_comp" class="in-box" placeholder="출판사" />
					<br />

					<form:input path="b_year" class="in-box" placeholder="출판년도" />
					<br />

					<form:input path="b_iprice" class="in-box" placeholder="판매가격" />
					<br />
				<button class="submit ml-auto" >저장</button>
			</form:form>
		</section>
	</div>
</body>
</html>