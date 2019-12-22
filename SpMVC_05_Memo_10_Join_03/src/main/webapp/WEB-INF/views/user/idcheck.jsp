<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function() {
		$("#id_use").click(function() {

			// 회원가입 창의 u_id input box에
			// 현재창의 u_id 값을 보내기
			$("#u_id", opener.document).val($("#u_id").val())

			// 현재 창 닫기
			window.close()

			// IE 창 닫기
			window.open('about:blank', '_self').self.close()
		})
	})
</script>
</head>
<body>
	<!-- when : if역할 -->
	<!-- otherwise : else역할 -->
	<c:choose>
		<c:when test="${ID_YES}">
			<h3>이미 가입된 ID</h3>
			<h5>다른 ID를 입력하라</h5>
		</c:when>
		<c:when test="${ID_YES == false && empty u_id}">
		<h3>ID를 입력하라</h3>
		</c:when>
		<c:otherwise>
			<h3>사용할 수 있는 ID</h3>
		</c:otherwise>
	</c:choose>
	<form>
		<input name="u_id" id="u_id" value="${u_id}">
		<button>다시검색</button>
		<button type="button" id="id_use">id 사용</button>
	</form>

</body>
</html>