<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<div class="col-xl-2 col-md-5 col-12">
	<div class="card">
		<div class="card-header">${NAVER.m_title}</div>
		<div class="card-body">
			<img src="${NAVER.m_image_url}" width="100%">
		</div>
		<div class="card-footer">
			<a href="${NAVER.m_datail_url}">자세히보기</a>
		</div>
	</div>
</div>