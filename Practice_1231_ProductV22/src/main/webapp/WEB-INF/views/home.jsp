<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>
div {
	margin: 5px;
}

div.row {
	margin: 0 auto;
}

div#p_list {
	height:600px;
	overflow:auto;
}

#p_image:hover {
	background-color:rgba(0,0,0,0.5);
	z-index: 10;
	
}
</style>
<script>
	$(function(){
		
		$("#p_image").click(function(){
			if(confirm("이미지를 삭제할까요")){
				let p_code = $("#p_code").val()
				document.location.href="${rootPath}/imgDelete?p_code=" + p_code
			}
		})
		
	})
</script>
</head>
<body>
	<header class="container-fluid fumbotron text-center bg-success">
		<h3>상품정보 관리</h3>
		<p>대한 쇼핑몰 상품정보 관리자화면
	</header>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<ul>
			<li class="navbar-brand nav-link" href="#">home</li>
			<li class="nav-item"><a class="nav-link" href="#">상품등록</a></li>
			<li class="nav-item"><a class="nav-link" href="#">재고관리</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">관리자</a></li>
		</ul>
	</nav>
	<section class="container-fluid">
		<article class="row d-flex justify-content-center">
			<div id="p_list" class="col-log-4 col-md-5 col-sm-11">
				<%@ include file="/WEB-INF/views/p-list.jsp"%>
			</div>
			<section class="col-lg-4 col-md-5 col-sm-11">
				<form class="was-vaildated" method="POST"
					enctype="multipart/form-data" action="${rootPath}/input">

					<div class="form-group">
						<label for="p_code">상품코드</label> 
							<input type="text" 
							class="form-control" 
							placeholder="상품코드를 입력하라" 
							name="p_code"
							id="p_code">
					</div>

					<div class="form-group">
						<label for="p_name">상품이름</label> 
							<input type="text"	class="form-control" 
								placeholder="상품이름를 입력하라" 
								name="p_name"
								id="p_name" required>
						<div class="valid-feedback">Valid</div>
						<div class="invalid-feedback">상품이름을 입력하세요</div>
					</div>

					<div class="form-group">
						<label for="p_iprice">매입가</label> 
							<input type="text" 
								class="form-control" 
								placeholder="매입가를 입력하라" 
								name="p_iprice"
								id="p_iprice" required>
						<div class="valid-feedback">Valid</div>
						<div class="invalid-feedback">매입가를 입력하세요</div>
					</div>

					<div class="form-group form-check">
						<label class="form-check-label"> 
							<input class="from-check-input" 
									type="checkbox" 
									name="p_vat" 
									value="1">과세
						</label>
					</div>

					<div class="form-group">
						<label for="p_oprice">매출가</label> 
						<input type="text" 
								class="form-control" 
								placeholder="상품코드를 입력하라" 
								name="p_oprice"
								id="p_oprice" required>
						<div class="valid-feedback">Valid</div>
						<div class="invalid-feedback">매출가를 입력하세요</div>
					</div>
					<div class="form-group">
						<input type="hidden"
								name="p_file"
								id="p_file">
								<label></label>
						<input type="file"
								class="form-controller"
								name="u_file"
								id="p_oprice">
					</div>
					<div class="form-group">
						<img src="${rootPath}/files/noimage.png" id="p_image" width="200px" height="200px">				
					</div>
					<button type="reset" class="btn btn-warning">새로작성</button>
					<button type="submit" class="btn btn-primary">저장</button>
				</form>
			</section>
			<div id="p_list" class="col-lg-3 col-md-5 col-sm-11">
				보조정보
			</div>
		</article>
	</section>
	<footer class="container-fluid bg-primary">
		<address class="text-center text-white">Copy Right &copy; jjong9950@naver.com</address>
	</footer>
</body>
</html>