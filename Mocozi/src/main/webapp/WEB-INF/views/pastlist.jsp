<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<!-- 컨테이너 및 main-subject, sub-title 설정 요소 값들-->
<link rel="stylesheet" href="${rootPath}/css/main.css">
<!-- List 설정 요소들 -->
<link rel="stylesheet" href="${rootPath}/css/curlist.css">
<link rel="stylesheet" href="${rootPath}/css/pastlist.css">


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

		<div class="list">
			<c:choose>
				<c:when test="${BASELIST==null}">
					검색 결과가 없습니다.
				</c:when>
				<c:otherwise>
					<c:forEach items="${BASELIST}" var="vo">
						<div class="list-item">
							<img class="list-item-img" src="${vo.firstimage2}">
							<div class="list-item-textarea">
								<div class="list-item-title">
									<a href="${rootPath}/tour/detail?contentid=${vo.contentid}&areacode=${vo.areacode}&sigungucode=${vo.sigungucode}">${vo.title}</a>
								</div>
								<div class="list-item-term">
									<c:choose>
										<c:when test="${vo.eventstartdate == null}">
											전화번호 : ${vo.tel}
										</c:when>
										<c:otherwise>
											${vo.eventstartdate} ~ ${vo.eventenddate}
										</c:otherwise>
									</c:choose>
								</div>
								<div class="list-item-location">위치 : ${vo.addr1}</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
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