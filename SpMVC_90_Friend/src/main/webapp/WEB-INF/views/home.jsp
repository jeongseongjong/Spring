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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</head>

<style>
.fList {
	font-size: 30px;
	font-weight: bolder;
	text-align: center;
}
</style>
<script>
	$(function() {

		$(".search").keypress(function(event) {
			//	document.location.href="${rootPath}/book/write"

			if (event.keyCode == 13) {

				let strText = $(".search").val()

				if (strText == "") {
					alert("이름 또는 전화번호를 입력하세요")
					return false;
				}

				alert(strText)

				$.get({
					url : "${rootPath}/searchName",
					data : {
						search : strText
					},
					success : function(result) {
						$("${rootPath}/searchName").html()
					}
				})
			}
		})
	})
</script>
<body>
	<div class="fList">친구 리스트</div>
	<form action="${rootPath}/searchName">
		<input type="text" class="search" name="search">
	</form>
	<section class="row container-fluid">
		<c:forEach items="${FLIST}" var="F">
			<div class="list col-xl-2 col-md-5 col-12">
				<div class="col-list-body card-body">
					<div></div>
					<div>
						${F.f_id}. 이름 : ${F.f_name}
						<button type="button"
							onclick="location.href = 'update?f_id='+ ${F.f_id}">수정</button>
						<button type="button"
							onclick="location.href = 'delete?f_id='+ ${F.f_id}">삭제</button>
					</div>
				</div>
				<div class=" card-footer">
					<div>전화번호 : ${F.f_tel}</div>
					<div>주소 : ${F.f_addr}</div>
					<div>취미 : ${F.f_hobby}</div>
					<div>관계 : ${F.f_relation}</div>
				</div>
			</div>
		</c:forEach>
		<button type="button" onclick="location.href = 'insert'">친구등록</button>
	</section>
</body>
</html>