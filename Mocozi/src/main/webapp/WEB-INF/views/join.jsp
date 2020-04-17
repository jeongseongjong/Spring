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

		$("#id_check").click(function() {
			let u_id = $("#u_id").val()
			let u_result = $(".overlap-check-result").val()
			$.ajax({
				type : "POST",
				url : '${rootPath}/member/idcheck',
				data : {
					'u_id' : u_id
				},
				dataType : 'json',
				success : function(result) {

					if(result){
						$(".overlap-check-result").text("사용가능한 ID")
					}else{
						$(".overlap-check-result").text("이미 사용중인 ID")
					}
				}
			})
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
					<form:input path="u_id" type="text" placeholder="사용할 ID를 입력하세요" />
					<br />
					<button id="id_check" class="check-btn" type="button">
						중복확인</button>
					<form:errors path="u_id" class="in-error" />
					<span id="u_id_msg"></span>
					<div class="overlap-check-result">
						
					</div>
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