<!-- html5와 이전으로 나눈다
	5부터 획기적으로 변화시작 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<style>

*, html, body{
	margin:10;
	padding:10;
}

/*
	header : tag 이름
	{} : 모양에 대한 여러가지 속성 지정
*/
header{
	background-color: gray; /* box의 바탕색 지정*/
	color : white; /* header box내의 문자열의 글자색 지정*/
	margin:0;
	padding:1rem;
}

ul{
/*
	displya:flex
	box model tag의 속성을
	inline-box 속성으로 변경하는 것
*/
	display: flex;
	list-style: none; /* list의 머릿글 제거 */
}

li{
	width : 100px;
	margin-right:10px;
	background-color:gray;
	color:green;
}

/* 태그만 시작하면 본문에 있는 모든 tag에 적용 */
a{
	text-decoration : none; /* a tag에 적용되어서 밑줄을 없애는 용도*/
	padding:10px;
}

/*
	a:hover 액션을 지정하는데
	nav tag 내에있는 a tag에만 액션을 지정하라
*/
nav a:hover{
	font-weight:bold; /* 글자모양을 bold체로 변경 */
	font-style:italic;
}


p{
 	background-color:cyan;
}


/*	이문서에 id가 p1으로 지정된 tag에 스타일을 지정하라 */
#p1{
	font-size:50pt;
	background-color:blue;
	color : white;
	
}

/* 이문서에 class가 pc로 지정된 tag에 스타일을 지정하라 */
/* p.pc 또는 .pc 형식으로 지정해도 된다. */
p.pc{
	background-color:red;
	color:white;
}
</style>
</head>
<body>
	<header>
		<h3>나의 홈페이지</h3>
	</header>
	<nav>
		<ul>
			<li><a href="#">학생리스트</a></li>
			<li><a href="#">학생검색</a></li>
			<li><a href="#">로그인</a></li>
			<li><a href="#">회원가입</a></li>
		</ul>
	</nav>
	<section>
		<article>
			<p><font size=30pt color=blue face=궁서>여기는 본문부분입니다.</font></p>
			<p style="font-size:50pt;color:white;background-color:gray">여기는 또다른 본문</p>
			<p>여기는 나의 이야기 입니다.</p>
			<p id="p1">여기는 나의 이야기 입니다.</p>
			<p id="p2">여기는 p2 입니다.</p>
			<p id="p3" class="pc">여기는 p3 입니다.</p>
			<p id="p4" class="pc">여기는 p4 입니다.</p>
			<p id="p5" class="pc">여기는 p5 입니다.</p>
			<p><a href="https://naver.com/">네이버 바로가기</a>
				<a href="https://daum.net/">다음 바로가기</a>
		</article>
	</section>
	<footer>
		<address>CopyRight &copy; jjong9950@naver.com</address>
	</footer>
</body>
</html>








