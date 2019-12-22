<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, 
  maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- 카카오맵 API -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=679e813a7d173421907253e756e5a51b"></script>
<!-- 컨테이너 및 main-subject, sub-title 설정 요소 값들-->
<link rel="stylesheet" href="${rootPath}/css/main.css">

<!-- 이하 Class들 설정 요소 값들-->
<link rel="stylesheet" href="${rootPath}/css/main.css">
<link rel="stylesheet" href="${rootPath}/css/curlist.css">
<link rel="stylesheet" href="${rootPath}/css/detail.css">

<style type="text/css">
.img-box {
	background-image: url(${rootPath}/img/winter.jpg);
}
</style>

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


		<div class="title">
			<img class="img-box" src="${detailCommonDTO.firstimage}">
			<div class="side-title">
				<h1>${detailCommonDTO.title}</h1>
				<h2>${detailIntroDTO.eventstartdate}~
					${detailIntroDTO.eventenddate}</h2>
				<h2>${detailIntroDTO.sponsor1}</h2>
				<h2>${detailCommonDTO.addr1}</h2>
				<div class="go-list">
					<a href="${rootPath}/tour/curlist?code=${AREACODE}">목록보기</a>
				</div>
			</div>
		</div>
		<div class="tab-menu" id="tab-introduce">
			<div class="tab-sub" id="btn-active">
				<a href="#tab-introduce">소개</a>
			</div>
			<div class="tab-sub">
				<a href="#tab-map">지도</a>
			</div>
			<div class="tab-sub">
				<a href="#tab-lodgments">숙박</a>
			</div>
			<div class="tab-sub">
				<a href="#tab-comment">의견</a>
			</div>
		</div>
		<div class="intro">
			관람 가능연령 : ${detailIntroDTO.agelimit}<br /> 행사 종료일 :
			${detailIntroDTO.eventenddate}<br /> 행사 홈페이지 :
			${detailCommonDTO.homepage}<br /> 행사 장소 :
			${detailIntroDTO.eventplace}<br /> 행사 시작일 :
			${detailIntroDTO.eventstartdate}<br /> 공연시간 :
			${detailIntroDTO.playtime}<br /> 관람 소요시간 :
			${detailIntroDTO.spendtimefestival}<br /> 주최자 정보 :
			${detailIntroDTO.sponsor1}<br /> 주최자 연락처 :
			${detailIntroDTO.sponsor1tel}<br /> 주관사 정보 :
			${detailIntroDTO.sponsor2}<br /> 주관사 연락처 :
			${detailIntroDTO.sponsor2tel}<br /> 이용요금 :
			${detailIntroDTO.usetimefestival}<br /> 개요 :
			${detailCommonDTO.overview}<br />

		</div>
		<div class="tab-menu" id="tab-map">
			<div class="tab-sub">
				<a href="#tab-introduce">소개</a>
			</div>
			<div class="tab-sub" id="btn-active">
				<a href="#tab-map">지도</a>
			</div>
			<div class="tab-sub">
				<a href="#tab-lodgments">숙박</a>
			</div>
			<div class="tab-sub">
				<a href="#tab-comment">의견</a>
			</div>
		</div>
		<div class="map">
			<div id="map"
				style="width: 900px; height: 450px; margin: 0 auto; margin-top: 20px; border-radius: 10px"></div>
		</div>
		<div class="tab-menu" id="tab-lodgments">
			<div class="tab-sub">
				<a href="#tab-introduce">소개</a>
			</div>
			<div class="tab-sub">
				<a href="#tab-map">지도</a>
			</div>
			<div class="tab-sub" id="btn-active">
				<a href="#tab-lodgments">숙박</a>
			</div>
			<div class="tab-sub">
				<a href="#tab-comment">의견</a>
			</div>
		</div>
		<div class="lodgments">
			<c:choose>
				<c:when test="${stayList != null}">
					<c:forEach items="${stayList}" var="vo">
						<div class="lodgment">
							<img class="lodgment-img" src="${vo.firstimage}"></img>
							<div class="lodgment-introduce">
								<div class="lodgment-title">${vo.title}</div>
								<div class="lodgment-location">${vo.addr1}</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>인근 숙박업소가 없습니다.</p>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="tab-menu" id="tab-comment">
			<div class="tab-sub">
				<a href="#tab-introduce">소개</a>
			</div>
			<div class="tab-sub">
				<a href="#tab-map">지도</a>
			</div>
			<div class="tab-sub">
				<a href="#tab-lodgments">숙박</a>
			</div>
			<div class="tab-sub" id="btn-active">
				<a href="#tab-comment">의견</a>
			</div>
		</div>
		<div class="comment">
			<c:choose>
				<c:when test="${commentList == null}">
					댓글 목록이 없습니다.
				</c:when>
				<c:otherwise>
					<c:forEach items="${commentList}" var="vo">
						<div class="comment-box">
							<div class="comment-sub-box">
								<div class="comment-sub-box-title">작성자</div>
								<div class="comment-sub-box-text">${vo.c_writer}</div>
							</div>
							<div class="comment-sub-box">
								<div class="comment-sub-box-title">작성일</div>
								<div class="commentsub-box-text">${vo.c_date}</div>
							</div>
							<div class="comment-sub-box">
								<div class="comment-sub-box-title">글내용</div>
								<div class="comment-sub-box-text">${vo.c_text}</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<fieldset>
				<legend>Write Comment</legend>
				<form action="${rootPath}/comment/write" method="POST">
					<div class="comment-sub-box">
						<div class="comment-sub-box-title">작성자</div>
						<div class="comment-sub-box-text">
							<input class="comment-input" type="text" name="writer">
						</div>
					</div>
					<br />
					<div class="comment-sub-box">
						<div class="comment-sub-box-title">글내용</div>
						<div class="comment-sub-box-text">
							<textarea name="text"></textarea>
						</div>
					</div>
					<input class="comment-input" type="hidden" name="contentid"
						value="${detailCommonDTO.contentid}"> <input
						class="comment-input" type="hidden" name="areacode"
						value="${detailCommonDTO.areacode}"> <input
						class="comment-input" type="hidden" name="sigungucode"
						value="${detailCommonDTO.sigungucode}">
					<button class="btn_writecomment">글쓰기</button>
				</form>
			</fieldset>
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

	<div class="move-top-page">
		<a href="#"><i class="fas fa-angle-double-up fa-2x"></i></a>
	</div>

</body>

<script>
	
		var container = document.getElementById('map');
		var options = {
				center: new kakao.maps.LatLng(${detailCommonDTO.mapy}, ${detailCommonDTO.mapx}),
				level: 3
			};
		// 마커가 표시될 위치입니다 
		var markerPosition  = new kakao.maps.LatLng(${detailCommonDTO.mapy}, ${detailCommonDTO.mapx}); 

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});
		
		var map = new kakao.maps.Map(container, options);
		
		marker.setMap(map);
		
		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		
		var iwContent = '<div style="width:200px; padding:10px">${detailCommonDTO.addr1}</div>'
	    iwPosition = new kakao.maps.LatLng(${detailCommonDTO.mapy}, ${detailCommonDTO.mapx}); //인포윈도우 표시 위치입니다

		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
		});
	  
		// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
		infowindow.open(map, marker);
		
</script>
</html>