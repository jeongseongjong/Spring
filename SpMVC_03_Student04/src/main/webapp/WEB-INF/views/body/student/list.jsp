<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<p></p>
<p></p>
<p></p>
<style>

table {
	border-collapse: collapse;
	border-spacing:0;
	width: 90%;
	border: 1px solid blue;
	border: 1px dotted blue;
	
	/* 
		top, bottom 여백을 20px로 하고 
		left와 right 여백을 자동으로 하라
		좌우 중앙에 박스를 위치하라
	*/
	margin:20px auto;
}

table tr {
	border: 1px dotted red;
}



/* td, th : td tag와 th tag의 공통된 속성을 부여하겠다. */
table td, table th{
	padding: 8px;
	vertical-align:top;
}

table td{
		/* 좌측정렬 */
	text-align : left;
}

 /* tr이 가지고 있는 태그들에게 지정 */
table tr:nth-child(odd){
	background-color : #fff;
}

table tr:nth-child(even){
	background-color : #ccc;
}

table tr:hover {
	background-color: gray;
	cursor: pointer;
}

</style>
<table>
<tr>
	<th>번호</th>
	<th>이름</th>
	<th>학과</th>
	<th>학년</th>
	<th>전화번호</th>
</tr>

	<c:choose>
		<c:when test="${STDLIST == NULL}">
			<tr>
				<td colspan="5">데이터가 없습니다.</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${STDLIST}" var="dto">
				<tr>
					<td>${dto.st_num}</td>
					<td>${dto.st_name}</td>
					<td>${dto.st_dept}</td>
					<td>${dto.st_grade}</td>
					<td>${dto.st_tel}</td>

				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>


</table>
