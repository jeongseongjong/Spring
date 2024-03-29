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
<script type="text/javascript">
	$(function() {

		$("#btn-save").click(function() {
/*
			let pass = $("#u_password").val()
			if (pass == "") {
				alert("비밀번호를 입력하라")
				$("#u_password").focus()
				return false
			}
			let re_pass = $("#re_password").val()
			if (re_pass == "") {
				alert("비밀번호를 한번 더 입력하라")
				$("#re_password").focus()
				return false
			}

			if (pass != re_pass) {
				alert("비밀번호가 일치하지 않다\n" + "다시 입력해라")
				$("#u_password").val("")
				$("#re_password").val("")
				$("#u_password").focus()
				return false
			}
*/
			let u_name = $("#u_name").val()
			if (u_name == "") {
				alert("이름은 반드시 입력해야 한다")
				$("#u_name").focus()
				return false;
			}

			$("form").submit()
		})

	})
</script>
<style>
fieldset {
	width: 70%;
	margin: 20px auto;
	border: 1px solid green;
	border-radius: 10px;
}

legend {
	font-weight: bold;
	font-size: 20px;
}

input, textarea {
	display: inline-block;
	width: 90%;
	padding: 8px;
	margin: 5px;
	border-radius: 20px
}

input:focus, textarea:focus button {
	border: 2px solid blue;
	outline: none;
}

input:hover {
	background-color:#ddd;
	border:2px solid red;
}

.in-error {
	display:inline-block;
	margin-left:20px;
	font-size:12px;
	color:red;
}


</style>
</head>
<body>
	<fieldset>
		<legend>
			<c:if test="${TITLE == null}">회원가입</c:if>
			<c:if test="${TITLE != null}">${TITLE}</c:if>
		</legend>
		<form:form modelAttribute="userDTO" autocomplete="on"
			class="user-form">

			<div class="in-box-border">
				<form:input path="u_id" class="in-box" type="text"
					placeholder="사용자 ID를 입력하라" />
				<br />
				<!-- form:input의 path와 form:errors의 path는 같아야한다 -->
				<form:errors path="u_id" class="in-error" />
			</div>

			<div class="in-box-border">
				<form:input path="u_password" type="password" class="in-box"
					placeholder="비밀번호를 입력하라" />
				<br />
				<form:errors path="u_password" class="in-error" />
			</div>

			<%
				/*
								비밀번호 확인 input box를 표준 html로 작성
								비밀번호 확인은 서버로 데이터를 전송할 필요가 없기 때문에
								또한 form:input으로 작성을 하게 되면
								DTO에 해당 필드변수를 작성해야하는 불편이 있기 때문에 
								이 항목에 입력된 값은 서버로 전송하기 전
								두번 입력한 비밀번호가 같은지 검사만하면 되기 때문에
							*/
			%>
			
			<div class="in-box-border">
				<input type="password" id="re_password"
					placeholder="비밀번호를 다시한번 입력하라">
					<from:errors path="re_password" class="in-error"/>
			</div>

			<div class="in-box-border">
				<form:input path="u_name" class="in-box" placeholder="이름을 입력하라" />
				<br />
				<form:errors path="u_name" class="in-error" />
			</div>

			<div class="in-box-border">
				<form:input path="u_nick" class="in-box" placeholder="별명을 입력하라" />
				<br />
				<form:errors path="u_nick" class="in-error" />
			</div>
			<div class="in-box-border">
				<form:input path="u_tel" type="number" class="in-box"
					placeholder="전화번호를 숫자로 입력하라" />
				<br />
				<form:errors path="u_tel" class="in-error" />
			</div>


			<button type="button" id="btn-save">저장</button>

		</form:form>
	</fieldset>
</body>
</html>