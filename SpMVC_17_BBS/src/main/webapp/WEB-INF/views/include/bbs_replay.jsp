<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach var="BBS" items="${BBS_SUB}" varStatus="index">
	<tr class="bbs_tr" data-bbs-id="${BBS.bbs_id}">
		<td>&nbsp;&nbsp;${BBS.bbs_p_id}-${index.count})</td>
		<td>${BBS.bbs_writer}</td>
		<td>&nbsp;&nbsp;${BBS.bbs_subject}</td>
		<td>${BBS.bbs_date}</td>
		<td>${BBS.bbs_time}</td>
	</tr>
</c:forEach>
