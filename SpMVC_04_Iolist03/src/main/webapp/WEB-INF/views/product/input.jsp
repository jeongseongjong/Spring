<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<%@ include file="/WEB-INF/views/include/include-css.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-product-header.jspf"%>
	<style>
.in-box {
	display: inline-block;
	width: 70%;
}

.in-box>input {
	padding: 8px;
	margin: 3px;
	display: inline-block;
	width: 70%;
	border: 1px solid #ccc;
}

.in-box>input:hover {
	background-color: #ddd;
	border: 1px solid blue;
}

.in-label {
	display: inline-block;
	width: 20%;
	text-align: right;
	margin-right: 5px;
	padding: 8px;
}

fieldset {
	width:70%;
	margin:20px auto;
	border-radius:20px;
	
}
legend{
	font-size:12px;
	font-weight:bold;
	color: #3d65ff;
}
</style>
	<form method="POST">
	
		<fieldset>
			<legend>상품 정보 입력</legend>
<!-- 
	label의 for 속성 : input box와 한 그룹으로 설정
	label을 클릭했을때 마치 input box를 클릭한 것처럼
	input box에 focus를 지정하는 것
 -->
			<label for="p_code" class="in-label">상품코드</label>
			<div class="in-box">
				<input name="p_code" id="p_code" value="${PI.p_code}" readonly="readonly">
			</div>

			<label for="p_name" class="in-label">상품명</label>
			<div class="in-box">
				<input name="p_name" id="p_name" value="${PI.p_name}">
			</div>

			<label for="p_iprice" class="in-label">매입단가</label>
			<div class="in-box">
				<input name="p_iprice" id="p_iprice" value="${PI.p_iprice}">
			</div>
			
			<label for="p_oprice" class="in-label">매출단가</label>
			<div class="in-box">
				<input name="p_oprice" id="p_oprice" value="${PI.p_oprice}">
			</div>
			
			<label for="p_vat" class="in-label">부가세</label>
			<div class="in-box">
				<input name="p_vat" id="p_vat" value="${PI.p_vat}">
			</div>

			<label class="in-label"></label>
			<div class="in-box">
				<button id="btn-save">저장</button>
			</div>
		</fieldset>
	</form>
</body>
</html>