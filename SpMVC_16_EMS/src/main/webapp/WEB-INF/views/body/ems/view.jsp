<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="main-list">
	<tr>
		<th>NO</th>
		<td>${emailVO.emsSeq}</td>
	</tr>
	<tr>
		<th>받는Email</th>
		<td>${emailVO.fromEmail}</td>
		<th>받는사람</th>
		<td>${emailVO.fromName}</td>
	</tr>
	<tr>
		<th>작성일자</th>
		<td>${emailVO.sendDate}</td>
		<th>작성시각</th>
		<td>${emailVO.sendTime}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${emailVO.subject}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${emailVO.content}</td>
	</tr>
</table>
<div style="padding: 10px 25px">
	<a href="${rootPath}/ems/update/${emailVO.emsSeq}">수정</a>
	<a href="${rootPath}/ems/delete/${emailVO.emsSeq}">삭제</a>
</div>