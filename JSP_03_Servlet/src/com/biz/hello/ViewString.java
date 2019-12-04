package com.biz.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewString
 */
@WebServlet("/viewString")
public class ViewString extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewString() {
        super();
        // TODO Auto-generated constructor stub
    }

    // request : 사용자의 요청정보를 알아낼 수 있는 객체
    // response : 응답에 관련된 객체
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> strNames = new ArrayList<String>();
		
		strNames.add("홍길동");
		strNames.add("이몽룡");
		strNames.add("성춘향");
		strNames.add("장영실");

		strNames.add("장녹수");
		strNames.add("장영자");

		// 응답할 문서 타입과 인코딩 방식을 지정
		response.setContentType("text/html;charset=UTF-8");
		
		// 응답 문서에 출력하기 위한 PrintWriter 객체를 가져온다.
		PrintWriter wOut = response.getWriter();
		for(String name : strNames) {
			wOut.printf("<p>%s</p>",name);
		}
		wOut.close();
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
