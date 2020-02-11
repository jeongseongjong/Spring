<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Bootstrap core CSS -->
<link href="${rootPath}/css/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${rootPath}/css/css/heroic-features.css" rel="stylesheet">
<script src="${rootPath}/css/vendor/jquery/jquery.min.js"></script>
<script
	src="${rootPath}/css/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<title>□□□ 나의 JSP 페이지 □□□</title>
</head>

<style>
body {
	margin-top: 30px;
}

.card-text {
	display: flex;
}

.card-img-top {
	width: 100px;
	height: 200px;
	margin-right: 10px;
}

.btn-box {
	float: right;
	display: flex;
}

.b-row {
	text-align: left;
}


</style>

<script>
	$(function() {

		$(".b-row").click(
				function() {

					let rb_bcode = $(this).attr("data-id")

					let rb_bname = $(this).attr("data-name")
					alert("기록할 도서명 : " + rb_bname)

					document.location.href = "${rootPath}/read/view-code?id="
							+ rb_bcode

				})

		$("#delete").click(function() {
			if (confirm("삭제 할까요 ?")) {

				return true;
			}

		})

		$("#update").click(function() {
			if (confirm("수정 하시겠습니까?")) {

				return true;
			}
		})

	})
</script>
<body>
	<!-- Page Content -->
	<div class="container">

		<!-- Page Features -->
		<div class="row text-center">
			<c:forEach items="${PLIST}" var="vo">
				<div class="col-lg-4 col-md-6 mb-4">
					<div class="card h-100">
						<div class="card-body">
							<div class="b-list card-text">
								<img class="card-img-top" src="http://placehold.it/100x50"
									alt="">
								<div class="content-box">
									<h4 class="card-title">${vo.b_name}</h4>
									<div class="b-row" data-id="${vo.b_code}"
										data-name="${vo.b_name}">
										<div>ISBN : ${vo.b_code}</div>
										<div>저자 : ${vo.b_auther}</div>
										<div>출판사 : ${vo.b_comp}</div>
										<div>출판년도 : ${vo.b_year}</div>
										<div>판매가격 : ${vo.b_iprice}</div>
									</div>
									<div class="btn-box">
										<div id="update">
											<a href="${rootPath}/update?id=${vo.b_code}">수정</a>
										</div>
										&nbsp;&nbsp;&nbsp;
										<div id="delete">
											<a href="${rootPath}/delete?id=${vo.b_code}">삭제</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- /.row -->
	</div>
	<c:choose>
		<c:when test="${empty PLIST}">
			<div>데이터가 없습니다.</div>
		</c:when>
		<c:otherwise>
			<div class="pagination">
				<%@ include file="/WEB-INF/views/pagination.jsp"%>
			</div>
		</c:otherwise>
	</c:choose>
	<!-- /.container -->
</body>
</html>