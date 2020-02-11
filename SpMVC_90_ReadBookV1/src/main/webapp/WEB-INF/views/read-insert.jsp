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
<style>
.insert-box {
	display: grid;
	margin-top: 20px;
	margin-bottom: 20px;
}

#readDTO {
	width: 100%;
	display: contents;
}

.submit {
	width: 100px;
}
</style>
</head>


<body>
	<div class="container">
		<section class="insert-box container">
			<h3>독서록 작성</h3>
			<form:form modelAttribute="readDTO" id="readDTO" autocomplete="on">
				<form:input type="hidden" path="rb_seq" class="in-box"
					placeholder="번호" />
				<br />

				<form:input path="rb_bcode" class="in-box text-muted"
					placeholder="도서코드" />
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

				<button class="submit ml-auto">저장</button>
			</form:form>
		</section>
	</div>
</body>
</html>