<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- 컨테이너 및 main-subject, sub-title 설정 요소 값들-->
<link rel="stylesheet" href="${rootPath}/css/main.css">

<!-- 로그인 페이지 요소 설정 값들-->
<!-- 로그인 요소를 일부 사용하고 아래에서 재설정했습니다. 순서대로 배치해주세요.-->
<link rel="stylesheet" href="${rootPath}/css/login.css">
<link rel="stylesheet" href="${rootPath}/css/join.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {

		function idcheck() {
			let top = 500

			let left = 500

			// alert("ENTER입력")
			let u_id = $("#u_id").val()
			let status = "toolbar=no,"
			status += "scrollbars=yes,"
			status += "resizable=no,"
			status += "top=500,"
			status += "left= 500,"
			status += "width=700,"
			status += "height=400"

			if (u_id == "") {
				alert("ID를 입력한 후 Enter")
				return false
			}

			openWin = window.open("${rootPath}/member/idcheck?u_id=" + u_id,
					"_blank", status)
			//	openWin.moveTO(left,top)
		}

		$("#u_id").keypress(function(e) {
			if (e.keyCode == 13) {
				idcheck()
			}
		})

		$("#id_check").click(idcheck)
		$("#btn-save").click(function() {
			let pass = $("#u_password").val()
			if (pass == "") {
				alert("비밀번호를 입력하세요")
				$("#u_password").focus()
				return false
			}
			let re_pass = $("#re_password").val()
			if (re_pass == "") {
				alert("비밀번호를 한번 더 입력하세요")
				$("#re_password").focus()
				return false
			}

			if (pass != re_pass) {
				alert("비밀번호가 일치하지 않습니다.")
				$("#u_password").val("")
				$("#re_password").val("")
				$("#u_password").focus()
				return false
			}
			$("form").submit()
		})
	})
</script>

</head>
<body>
	<fieldset>
		<div class="container">
			<div class="login-box">
				<div class="main-subject">회원가입</div>
				<form:form modelAttribute="userDTO" autocomplete="on"
					class="user-form">
					<div class="text-box">아이디</div>
					<form:input path="u_id" type="text" placeholder="사용할 ID를 입력하세요" /><br/>
					<button id="id_check" class="check-btn" type="button">
						중복확인
					</button>
					<form:errors path="u_id" class="in-error" />
					<span id="u_id_msg"></span>
					<div class="overlap-check-result">사용 여부 나오는 부분</div>
					<div class="text-box">비밀번호</div>
					<div>
					<form:input path="u_password" type="password"
						class="text-box-warning" placeholder="문자,숫자를 합하여 8자리 이상 입력하세요" />
					
					<form:errors path="u_password" class="in-error" />
					</div>
					<div>
						<input type="password" id="re_password"
							placeholder="비밀번호를 다시한번 입력하세요">
						<form:errors path="re_password" class="in-error" />
					</div>

					<button type="button" id="btn-save">회원가입</button>

				</form:form>
			</div>
		</div>
	</fieldset>
</body>
</html>