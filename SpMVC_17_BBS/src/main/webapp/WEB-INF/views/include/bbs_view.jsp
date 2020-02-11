<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.card {
	width: 100%;
	border: none;
	text-align: left;
	float: right;
}

h3 {
	display: inline-block;
	padding: 5px 15px;
}

.btn-box {
	float: right;
}

.btn {
	margin: 5px;
	float: right;
}

.view {
	width: 100%;
	border: none;
	margin-top: 20px;
}

p {
	border-bottom: 1px solid #ccc;
	box-shadow: black;
}

.title {
	font-size: 20px;
}

textarea {
	width: 100%;
	border: 1px solid #ddd;
	box-shadow: 2px 2px 2px 2px gray;
}

.cont {
	border: none;
}
</style>
<script>
	$(function() {
		
		// 현재 페이지가 모두 그려진 후 바로 실행하라
		// cmt_list를 post방식으로 넘기고
		$.post("${rootPath}/bbs/cmt_list", {cmt_p_id:"${bbsVO.bbs_id}"}, function(result){

			$("#cmt_list").html(result)
			
		})

		$("button.btn-c-save").click(function() {

			let cmt_writer = $("#cmt_writer").val()
			let cmt_text = $("#cmt_text").val()

			if (cmt_writer == "") {
				alert("댓글 작성자를 입력하시오")
				$("#cmt_writer").focus()

				return false
			}

			if (cmt_text == "") {
				alert("댓글을 입력하시오")
				$("#cmt_text").focus()

				return false
			}

			let cmt_data = {
				cmt_p_id : '${bbsVO.bbs_id}',
				cmt_writer : cmt_writer,
				cmt_text : cmt_text
			}

			$.ajax({
				url : '${rootPath}/bbs/cmt_write',
				method : 'POST',
				data : cmt_data,
				success : function(result) {
					// alert(result)
					$("#cmt_list").html(result)
				},
				error : function() {
					alert("서버와 통신 오류")
				}

			})

		})
		
		

	})
</script>
<div class="cont">
	<div class="view">
		<article class="card">
			<div>
				<div class="title">제목</div>
				<h3>${bbsVO.bbs_subject}</h3>
			</div>

			<div>
				<div class="title">내용</div>
				<p>${bbsVO.bbs_content}</p>
			</div>

			<div>
				<div class="title">작성일</div>
				<p>${bbsVO.bbs_date}</p>
			</div>

			<div>
				<div class="title">작성시각</div>
				<p>${bbsVO.bbs_time}</p>
			</div>

			<div>
				<div class="title">작성자</div>
				<p>${bbsVO.bbs_writer}</p>
			</div>

			<div>
				<div id="cmt_list" class="form-group">댓글리스트 보기</div>
			</div>

			<div class="container-fluid">
				<div class="form-group">
					<input name="cmt_writer" id="cmt_writer" class="form-control"
						placeholder="작성자">
				</div>
				<div class="form-group">
					<input name="cmt_text" id="cmt_text" class="form-control">
					<button class="btn btn-dark btn-c-save">저장</button>
				</div>
			</div>

		</article>



		<div class="btn-box btn-group">
			<button type="button" class="btn btn-secondary">리스트보기</button>
			<button type="button" class="btn btn-secondary">수정</button>
			<button type="button" class="btn btn-secondary">삭제</button>
		</div>
	</div>

	<c:if test="${bbsVO.bbs_p_id == 0}">
		<script>
			$(function() {

				$("button.btn-r-save").click(function() {

					let bbs_writer = $("#bbs_writer").val()
					let bbs_content = $("#bbs_content").val()

					if (bbs_writer == "") {
						alert("작성자를 입력하세요")
						$("#bbs_writer").focus()
						return false
					}

					if (bbs_content == "") {
						alert("답글 본문을 입력하세요")
						$("#bbs_content").focus()

						return false
					}

					$("form").submit()
				})
			})
		</script>
		<form:form action="${rootPath}/bbs/replay" modelAttribute="bbsVO">
			<div class="form-group">
				<input class="from-control" id="bbs_writer" name="bbs_writer"
					placeholder="답글작성자" />
			</div>
			<textarea name="bbs_content" id="bbs_content"></textarea>
			<button type="button" class="btn btn-info btn-r-save btn-dark">답글저장</button>
		</form:form>
	</c:if>
</div>



