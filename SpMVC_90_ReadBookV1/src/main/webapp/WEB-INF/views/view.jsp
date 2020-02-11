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

.r-row {
	text-align: left;
}

.btn-box {
	display: inline-block;
}

.btn {
	margin-right: 17px;
	margin-top: 5px;
}

.title-box {
	display: flex;
	justify-content: space-between;
}
</style>



<script>
	$(function() {

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

		$("#view").click(function() {
			if (confirm("상세보기 하시겠습니까?")) {

				return true;
			}
		})

	})
</script>
<body>
	<!-- Page Content -->
	<div class="container">
		<c:choose>
			<c:when test="${empty readList}">
				데이터가 비어있습니다.
			</c:when>
			<c:otherwise>
				<div class="title-box">
					<h2>독서록 리스트</h2>
				</div>
				<!-- Page Features -->
				<div class="row text-center">
					<c:forEach items="${readList}" var="vo">
						<div class="col-lg-12 col-md-12 mb-12">
							<div class="card h-100">
								<div class="card-body">
									<div class="r-list card-text">
										<img class="card-img-top" src="http://placehold.it/100x50"
											alt="">
										<div class="content-box">
											<div class="r-row" data-id="${vo.rb_bcode}">
												<div>ISBN : ${vo.rb_bcode}</div>
												<div>작성일 : ${vo.rb_date}</div>
												<div>독서시작시간 : ${vo.rb_stime}</div>
												<div>독서시간 : ${vo.rb_rtime}</div>
												<div>한줄소감 : ${vo.rb_subject}</div>
												<div>별점: ${vo.rb_star}</div>
												<div class="btn-box">
													<div>
														<div id="update">
															<a href="${rootPath}/read/update?id=${vo.rb_seq}">수정</a>
														</div>
														<div id="delete">
															<a href="${rootPath}/read/delete?id=${vo.rb_seq}">삭제</a>
														</div>
														<div id="view">
															<a href="${rootPath}/read/view-seq?id=${vo.rb_seq}">상세보기</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<c:choose>
						<c:when test="${!empty readList}">
							<%@ include file="/WEB-INF/views/pagination.jsp"%>
						</c:when>
					</c:choose>
				</div>
				<!-- /.row -->
			</c:otherwise>
		</c:choose>
	</div>
	<!-- /.container -->
</body>
</html>