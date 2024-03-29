<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function(){
		$(".email-list").click(function(){
			document.location.href = "${rootPath}/ems/view/" + $(this).data("seq")
		})
	})
</script>
<table class="main-list" >
	<tr>
		<th>NO</th>
		<th>받는Email</th>
		<th>받는사람</th>
		<th>제목</th>
		<th>작성일자</th>
		<th>작성시각</th>
	</tr>
	<c:choose>
		<c:when test="${empty LIST}">
			<tr>
				<td colspan="6">데이터가 없습니다</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${LIST}" var="VO" varStatus="in">
				<tr data-seq="${VO.emsSeq}" class="email-list">
					<td width="10%">${in.index}</td>
					<td width="12%">${VO.fromEmail}</td>
					<td width="12%">${VO.fromName}</td>
					<td width="42%">${VO.subject}</td>
					<td width="12%">${VO.sendDate}</td>
					<td width="12%">${VO.sendTime}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<div style="padding:10px 25px">
	<button id="btn-email-send" onclick="location.href='${rootPath}/ems/input' ">
		메일보내기
	</button>
</div>