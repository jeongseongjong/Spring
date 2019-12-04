$(function() {

	var calc = function() {

		// parseInt : 숫자로 바꾸는 기능
		let price = parseInt($("#io_price").val())
		let qty = parseInt($("#io_qty").val())

		let total = price * qty

		$("#io_total").val(total)

	}

	$("#btn-save").click(function() {

		let dCode = $("#io_dcode").val()
		if (dCode == "") {
			alert("거래처코드를 입력하라")
			$("#io_dcode").focus()
			return false
		}
		let pCode = $("#io_pcode").val()
		if (pCode == "") {
			alert("상품코드를 입력하라")
			$("#io_pcode").focus()
			return false
		}
		// button 키를 저장하는 기능
		if (confirm("저장하실?")) {
			$("form").submit()
		}

	})

	$("#io_price").on("change keyup paste input propertychange", calc)
	$("#io_qty").on("change keyup paste input propertychange", calc)

	// 입력박스에서 enter키가 입력감지를 위함
	$("#io_dcode").keypress(function(e) {
		if (e.keyCode == 13) {

			// 현재까지 거래처 코드 input box에 입력된 값을 추출
			let strText = $(this).val()

			// 이 코드를 가지고 새로운 창을 여는게 아래
			let query = rootPath
			query += "/dept/search?strText=" + strText

			let status = "toolbar=no,"
			status += "scrollbars=yes,"
			status += "resizable=no,"
			status += "top=200,"
			status += "left=200,"
			status += "width=400,"
			status += "height=400"

			// _blank가 있으므로
			// 브라우저를 열어서 조회하는 창을 보여라
			window.open(query, "_blank", status)
		}
	})

	/*
	 * 입력box에 키보드로 뭔가를 입력할때마다 발생하는 이벤트 
	 * 
	 * 키보드 이벤트 
	 * keydown 키보드를 누르는 순간 발생
	 * keypress 글자가 입력되어서 컴퓨터로 입력되는 순간 어떤 글자가 입력되었는가를 캐치하기 위한 이벤트
	 * keyup 키보드에서 손을 떼는 순간
	 */
	$("#io_pcode").keypress(function(event) {

		// 키보드에서 어떤 문자를 입력하면 event의 keycode라는 속성에
		// 문자의 ASCII 코드값이저장되어 전달된다 (ASCII 13 : ENTER키, 28 : ESC)
		if (event.keyCode == 13) {
			let str = $(this).val()
			let query = rootPath
			query += "/product/search?strText=" + str
			let status = "toolbar=no,"
			status += "scrollbars=yes,"
			status += "resizable=no,"
			status += "top=300,"
			status += "left=500,"
			status += "width=700,"
			status += "heigth=500"
			window.open(query, "_blank", status)
		}
	})
})