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
<script src="${rootPath}/js/jquery-3.4.1.js"></script>
<script>
	$(function(){
		$(".content-body").click(function(){
			let id = $(this).attr("data-id")
			let auth = $(this).attr("data-auth")
			
			document.location.href="${rootPath}/board/view?id="id
		})
	})
</script>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<th>작성일</th>
			<th>작성자</th>
			<th>제목</th>
		</tr>
		<c:choose>
			<c:when test="${BOARD_LIST} eq NULL">
				<tr>
					<td colspan="5">글이 없음</td>
			</c:when>
		</c:choose>
		<c:otherwise>
			<c:forEach items="${BOARD_LIST}" var="board" varStatus="index">
				<tr class="content-body" data-id="${board.b_seq}"
										data-auth="${board.b_auth}">
				<td>${index.count}</td>
				<td>${board.b_date}</td>
				<td>${board.b_auth}</td>
				<td>${board.b_title}</td>
			</c:forEach>
		</c:otherwise>
	</table>
</body>
</html>