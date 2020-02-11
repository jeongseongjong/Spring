<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
* {
	margin:0;
	padding:0;
	box-sizing:border-box;
}

.login-form {
	
	width:400px;
	padding:40px;
	background:#191919;
	text-align:center;
	z-index:10;
	
	border-radius:20px;
	box-shadow:12px 12px 2px 1px rgba(0, 0, 255, 0.2);
	
	margin:20px auto;
	
}

.login-form h2 {
	color:white;
	font-wieght:500;
}
.login-form h3 {
	color:white;
	font-wieght:300;
	background-color:red;
	border-radius:20px;
}

.login-form input {
	
	background:none;
	margin:20px auto;
	text-align:center;
	
	border:2px solid #3498db;
	
	padding : 14px 10px;
	width:200px;
	outline:none;
	color:white;
	
	border-radius :25px;
	transition:0.2s;
}

.login-form input:focus{
	width:280px;
	border-color:#2ECC71;
}

.login-form button{
	
	border:2px solid #22cc71;
	padding:14px 40px;
	background:none;
	display:block;
	margin:20px auto;
	padding: 14px 40px;
	
	outline:none;
	color:white;
	border-radius:25px;
	cursor : pointer;
}

.login-form button:hover{
	background-color:#2ecc71;
}

.naver_login img {
	border-radius : 10px;
}
/*
	div box에 image를 2개 가져오고
	초기에는 2번째 이미지를 감추어둔다
	
*/
.naver_login:hover img:last-child {
	display:inline-block;
}

/*
	마우스를 div박스에 올리면 처음이미지를 감추고
	두버째 이미지를 노출 시킨다.
*/
.naver_login img:last-child {
	display:none;
}

.naver_login:hover img:first-child {
	display:none;
}
</style>
<script>
$(function(){
	
	$(".naver_login").click(function(){
		
		document.location.href="${rootPath}/member/naver"
		
	})
	
	$("#btn-join").click(function(){
		document.location.href="${rootPath}/rest/member/join"
	})
	
	$("btn-login").click(function(){
	alert("abcd")
		// 유효성 검사
		// ID, Password가 입력되지 않았을 때
		let u_id = $("#u_id").val()
		if(u_id == ""){
			alert("아이디를 입력하세요")
			$("#u_id").focus();
			return false;
		}
		/*
		var params = $("form").serialize();
		$.ajax({
			url : "${rootPath}/member/login",
			type : 'POST',
			data : params,
			success : function(result){
				alert(result)
			}
		})
		*/
		$.post("${rootPath}/rest/member/login",
			$("form").serialize(),
			function(result){
			alert(result)
				document.location.href= document.location.href
			}
		)
	})
})
	
</script>

	<form method="POST" action="${rootPath}/member/login" class="login-form">
		<h2>로그인</h2>
		<c:if test="${LOGIN_MSG == 'FAIL'}">
		<h3>아이디 또는 패스워드가 잘못되었다.</h3>
		</c:if>
		<c:if test="${LOGIN_MSG == 'TRY'}">
		<h3>로그인을 하라</h3>
		</c:if>
		<c:if test="${LOGIN_MSG == 'NO_AUTH'}">
		<h3>작성자만 확인 가능</h3>
		</c:if>
		
		<c:if test="${LOGIN_MSG == '0'}">
		<h3>로그인을 환영합니다</h3>
		</c:if>
		
		<input type="text" id="u_id" name="u_id" placeholder="사용자 ID"> 
		<input type="password" id="u_password" name="u_password" placeholder="비밀번호">
		<button type="submit" id="btn-login">로그인</button>
		<div class="naver_login">
			<img src="${rootPath}/resources/image/naver_green.png" width="200px">
			<img src="${rootPath}/resources/image/naver_white.png" width="200px">
		</div>
		<button type="button" id="btn-join">회원가입</button>
	</form>
