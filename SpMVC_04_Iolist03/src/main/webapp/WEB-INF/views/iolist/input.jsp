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
	<%@ include file="/WEB-INF/views/include/include-dept-header.jspf"%>
	<style>
.in-box {
	display: inline-block;
	width: 70%;
}

.in-box > input {
	padding: 8px;
	margin: 3px;
	display: inline-block;
	width: 70%;
	border: 1px solid #ccc;
}

.in-box > input:hover {
	background-color: #ddd;
	border: 1px solid blue;
}

.in-box > select {
	padding: 8px;
	margin: 3px;
	display: inline-block;
	width: 72%;
	border: 1px solid #ccc;
}

.in-box > input[type="radio"] {
	padding: 8px 0;
	margin: 3px 0;
	display: inline-block;
	width: 73%;
	border: 1px solid #ccc;
}

.in-box > input#io_pcode, .in-box > input#io_dcode {
	width:30%;
	
}

	span{
		color:blue;
		font-weight:bold;
		font-style:italic;
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
			<legend>매입매출 입력</legend>
<!-- 
	label의 for 속성 : input box와 한 그룹으로 설정
	label을 클릭했을때 마치 input box를 클릭한 것처럼
	input box에 focus를 지정하는 것
 -->
 <%
 /*
 	IO_SEQ
	IO_DATE
	IO_INOUT
	IO_QTY
	IO_PRICE
	IO_TOTAL
	IO_PCODE
	IO_DCODE
 */
 %>
			<input name="io_seq" id="io_seq"  type="hidden"
				value="<c:out value='${IO_DTO.io_seq}' default='0'/>" >
			
			<label for="io_dcode" class="in-label">거래처코드</label>
			<div class="in-box">
				<input name="io_dcode" id="io_dcode" >
				<span id="io_dname">거래처명</span>
			</div>
			
			<label for="io_inout" class="in-label">거래구분</label>
			<div class="in-box">
				<select name="io_inout" id="io_inout">
					<option value="1">매입</option>
					<option value="2">매출</option>
				</select>
			</div>
			
			<label for="io_pcode" class="in-label">상품코드</label>
			<div class="in-box">
				<input name="io_pcode" id="io_pcode" >
				<span id="io_pname">상품이름</span>
			</div>
			
			<label for="io_date" class="in-label">거래일자</label>
			<div class="in-box">
				<input type="date" name="io_date" id="io_date" value="${IO_DTO.io_date}">
			</div>
			
			<label for="io_qty" class="in-label">수량</label>
			<div class="in-box">
				<input name="io_qty" id="io_qty" type="tel" 
					value="<c:out value='${IO_DTO.io_qty}' default='0'/>" >
			</div>
			
			<label for="io_price" class="in-label">단가</label>
			<div class="in-box">
				<input name="io_price" id="io_price" type="number"
					value="<c:out value='${IO_DTO.io_price}' default='0'/>" >
			</div>
			
			<label for="io_total" class="in-label">합계</label>
			<div class="in-box">
				<input name="io_total" id="io_total" value="<c:out value='${IO_DTO.io_total}' default='0'/>" >
			</div>
			
			<label class="in-label"></label>
			<div class="in-box">
				<button id="btn-save">저장</button>
			</div>
		</fieldset>
	</form>
</body>
</html>