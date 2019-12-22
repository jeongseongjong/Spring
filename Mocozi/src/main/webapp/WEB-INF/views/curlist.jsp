<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>

<!-- FontAwesome CSS-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<!-- swife link-->
<link rel="stylesheet"
	href="https://unpkg.com/swiper/css/swiper.min.css">
<script src="https://unpkg.com/swiper/js/swiper.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- List 설정 요소들 -->
<link rel="stylesheet" href="${rootPath}/css/main.css">
<link rel="stylesheet" href="${rootPath}/css/curlist.css">

</head>

<body>

	<div class="container">
		<div class="top-menu">

			<ul class="main-menu-box">
				<li class="drop-main"><a href="#"><i
						class="fas fa-bars fa-2x"></i></a>
					<ul class="drop-sub">
						<li><a
							href="${rootPath}/tour/curlist?code=${AREALIST[0].code}">${AREALIST[0].name}</a></li>
						<li><a
							href="${rootPath}/tour/curlist?code=${AREALIST[8].code}">${AREALIST[8].name}</a></li>
						<li><a
							href="${rootPath}/tour/curlist?code=${AREALIST[9].code}">${AREALIST[9].name}</a></li>
						<li><a
							href="${rootPath}/tour/curlist?code=${AREALIST[10].code}">${AREALIST[10].name}</a></li>
						<li><a
							href="${rootPath}/tour/curlist?code=${AREALIST[11].code}">${AREALIST[11].name}</a></li>
						<li><a
							href="${rootPath}/tour/curlist?code=${AREALIST[12].code}">${AREALIST[12].name}</a></li>
						<li><a
							href="${rootPath}/tour/curlist?code=${AREALIST[13].code}">${AREALIST[13].name}</a></li>
						<li><a
							href="${rootPath}/tour/curlist?code=${AREALIST[14].code}">${AREALIST[14].name}</a></li>
						<li><a
							href="${rootPath}/tour/curlist?code=${AREALIST[15].code}">${AREALIST[15].name}</a></li>
						<li><a
							href="${rootPath}/tour/curlist?code=${AREALIST[16].code}">${AREALIST[16].name}</a></li>
					</ul></li>
			</ul>
			<div class="keybox">
				<form action="${rootPath}/tour/search" method="get">
					<input class="se-key" name="keyword">
				</form>
			</div>
			<c:if test="${userDTO == null || userDTO.u_id == null }">
				<a id="login" href="#"><i class="fas fa-sign-in-alt fa-2x"></i></a>
			</c:if>
			<c:if test="${userDTO != null || userDTO.u_id != null }">
				<a href="${rootPath}/tour/main">${userDTO.u_id}</a>
				<a href="${rootPath}/member/login">로그아웃</a>
			</c:if>
		</div>

		<p class="main-subject">
			<a href="${rootPath}/">모꼬지</a>
		</p>
		<p class="sub" style="border-top-width: 50px; margin-top: 0px;">축제
			행사의 순 우리말 모꼬지 : 오늘은 어디 갈까?</p>

		<!-- Swiper -->
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<c:forEach items="${BASELIST}" var="vo">
					<div class="swiper-slide">
						<img class="list-item-img" src="${vo.firstimage}">
						<div class="list-item-textarea">
							<div class="list-item-title">
								<a
									href="${rootPath}/tour/detail?contentid=${vo.contentid}&areacode=${vo.areacode}&sigungucode=${vo.sigungucode}">${vo.title}</a>
							</div>
							<div class="list-item-term">${vo.eventstartdate}~
								${vo.eventenddate}</div>
							<div class="list-item-location">위치 : ${vo.addr1}</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- Add Pagination -->
			<div class="swiper-pagination"></div>
			<!-- Add Arrows -->
			<div class="swiper-button-next"></div>
			<div class="swiper-button-prev"></div>
		</div>

		<footer>
			<div>
				<a href="${rootPath}/tour/introduce">사이트소개</a>·<a
					href="${rootPath}/tour/alllist">전체축제</a>·<a
					href="${rootPath}/tour/pastlist?code=${AREACODE}">리스트로 보기</a>
			</div>
			<div class="addr">광주광역시 북구 경양로170(중흥동) 한경빌딩(구 남양건설빌딩)5층|한국경영원
				인재개발원|조장박제원|이정연|정성종</div>
			<div class="addr">@CopyRight 20191125-20191214 한국경영원 인재개발원 모꼬지</div>
		</footer>
	</div>




	<!-- Initialize Swiper -->
	<script>
		var swiper = new Swiper('.swiper-container', {
			slidesPerView : 1,
			spaceBetween : 30,
			loop : true,
			pagination : {
				el : '.swiper-pagination',
				clickable : true,
			},
			navigation : {
				nextEl : '.swiper-button-next',
				prevEl : '.swiper-button-prev',
			},
		});

		// click 부분

		$(function() {

			$("#login").click(function() {

				document.location.href = "${rootPath}/member/login"

			})

		})
	</script>
</body>

</html>