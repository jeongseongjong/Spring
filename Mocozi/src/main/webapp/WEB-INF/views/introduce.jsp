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

<!-- List 설정 요소들 -->
<link rel="stylesheet" href="${rootPath}/css/main.css">
<link rel="stylesheet" href="${rootPath}/css/introduce.css">

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
			<a id="login" href="${rootPath}/member/login"><i class="fas fa-sign-in-alt fa-2x"></i></a>
		</div>

		<p class="main-subject">
			<a href="${rootPath}/">모꼬지</a>
		</p>
		<p class="sub" style="border-top-width: 50px; margin-top: 0px;">축제
			행사의 순 우리말 모꼬지 : 오늘은 어디 갈까?</p>
		<div class=question-box>
			<h3 class="question">모꼬지는 어떤 사이트인가요 ?</h3>
			<p class="answer">각종 축제를 한눈에 볼 수 있는 사이트를 만들고 싶었어요.</p>
			<h3 class="question">특별한 기술이 있나요?</h3>
			<p class="answer">Drop-down을 사용하여 지역별로 구분했어요.</p>
			<p class="answer">Swiper를 사용하여 웹/앱에서 슬라이드 모션으로 쉽게 넘길 수 있도록 만들었어요.</p>
			<p class="answer">Open Api 사용하여 정확한 위치/자료를 제공했어요.</p>
			<h3 class="question">사이트를 구현하는데 힘든점은 없었나요 ?</h3>
			<p class="answer">박제원 : 기획의도에 벗어나지 않기 위해 신경을 많이 썼어요.</p>
			<p class="answer">이정연 : 메인 페이지와 슬라이드모션을 구현하고 디자인하는데 고민을 많이 했어요.</p>
			<p class="answer">정성종 : 드롭다운을 효율적이게 활용할 방법을 많이 고민했어요.</p>
		</div>

		<footer>
			<div>
				<a href="${rootPath}/tour/introduce">사이트소개</a>·<a href="${rootPath}/tour/alllist">전체축제</a>·<a
					href="${rootPath}/tour/pastlist?code=${AREACODE}">리스트로 보기</a>
			</div>
			<div class="addr">광주광역시 북구 경양로170(중흥동) 한경빌딩(구 남양건설빌딩)5층|한국경영원
				인재개발원|조장박제원|이정연|정성종</div>
			<div class="addr">@CopyRight 20191125-20191214 한국경영원 인재개발원 모꼬지</div>
		</footer>
	</div>

</body>

</html>