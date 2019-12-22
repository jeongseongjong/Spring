<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="${rootPath}/css/main.css">
<link rel="stylesheet" href="${rootPath}/css/curlist.css">
<link rel="stylesheet" href="${rootPath}/css/search-ticker.css">

<style>
#search {
	background-image: url(${rootPath}/img/실시간_검색어_테두리.png);
}
</style>

</head>
<body>
	<div class="container">
		<div class="top-menu">

			<ul class="main-menu-box">
				<li class="drop-main"><a href="#"><i class="fas fa-bars fa-2x"></i></a>
					<ul class="drop-sub">
						<li><a href="${rootPath}/tour/curlist?code=${AREALIST[0].code}">${AREALIST[0].name}</a></li>
						<li><a href="${rootPath}/tour/curlist?code=${AREALIST[8].code}">${AREALIST[8].name}</a></li>
						<li><a href="${rootPath}/tour/curlist?code=${AREALIST[9].code}">${AREALIST[9].name}</a></li>
						<li><a href="${rootPath}/tour/curlist?code=${AREALIST[10].code}">${AREALIST[10].name}</a></li>
						<li><a href="${rootPath}/tour/curlist?code=${AREALIST[11].code}">${AREALIST[11].name}</a></li>
						<li><a href="${rootPath}/tour/curlist?code=${AREALIST[12].code}">${AREALIST[12].name}</a></li>
						<li><a href="${rootPath}/tour/curlist?code=${AREALIST[13].code}">${AREALIST[13].name}</a></li>
						<li><a href="${rootPath}/tour/curlist?code=${AREALIST[14].code}">${AREALIST[14].name}</a></li>
						<li><a href="${rootPath}/tour/curlist?code=${AREALIST[15].code}">${AREALIST[15].name}</a></li>
						<li><a href="${rootPath}/tour/curlist?code=${AREALIST[16].code}">${AREALIST[16].name}</a></li>
					</ul></li>
			</ul>
			<div class="keybox">
				<form action="${rootPath}/tour/search" method="get">
					<input class="se-key" name="keyword">
				</form>
			</div>
			<a id="login" href="${rootPath}/member/login"><i class="fas fa-sign-in-alt fa-2x"></i></a>
		</div>

		<p class="main-subject">
			<a href="${rootPath}/">모꼬지</a>
		</p>
		<p class="sub" style="border-top-width: 50px; margin-top: 0px;">축제 행사의 순 우리말 모꼬지 : 오늘은 어디 갈까?</p>
	
		<footer>
			<div>
				<a href="${rootPath}/tour/introduce">사이트소개</a>·<a href="${rootPath}/tour/alllist">전체축제</a>·<a href="${rootPath}/tour/pastlist?code=${AREACODE}">리스트로 보기</a>
			</div>
			<div class="addr">광주광역시 북구 경양로170(중흥동) 한경빌딩(구 남양건설빌딩)5층|한국경영원 인재개발원|조장박제원|이정연|정성종</div>
			<div class="addr">@CopyRight 20191125-20191214 한국경영원 인재개발원 모꼬지</div>
		</footer>	
	</div>
</body>
</html>