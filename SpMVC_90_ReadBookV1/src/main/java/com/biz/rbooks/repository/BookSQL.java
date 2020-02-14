package com.biz.rbooks.repository;

import org.apache.ibatis.jdbc.SQL;

public class BookSQL {

	// 1. 가장 안에 있는 query부터 수행된다.
	// 2. SELECT /*+ INDEX_DESC(B) */ * FROM tbl_books B" 를 alias IP라고 지정했다.
	// 3. ROWNUM을 지정해서 테이블 데이터의 앞에 순번이 붙는다. ALIAS NUM라고했다.
	// 4. &lt; : <
	// 5. &gt; : >
	// 6. 뒤를 자르는 조건문을 실행
	// 7. 나머지에서 앞을 자르는 조건문 실행
	// 8. 쿼리문이 실행되는 값에 ALIAS를 꼭 넣어줘야한다.
	public static final String selectPage = 
			"<script>"
			+ "SELECT * FROM"
			+ "		("
			+ "			SELECT /*+ FIRST_ROWS_100 */ ROWNUM AS NUM, IP.* FROM" 
			+ "			("
			+ "				SELECT /*+ INDEX_DESC(B) */ * FROM tbl_books B" 
			+ "			)IP"
			+ "			WHERE ROWNUM &lt;= #{limit} " 
			+ "		)TBL"
			+ "		WHERE NUM &gt;= #{offset}"
			+ "</script>";
	
	public static final String search = 
			"<script>"
			+ "SELECT * FROM"
			+ "		("
			+ "			SELECT /*+ FIRST_ROWS_100 */ ROWNUM AS NUM, IP.* FROM" 
			+ "			("
			+ "				SELECT /*+ INDEX_DESC(B) */ * FROM tbl_books B WHERE b_name LIKE '%' || #{search} || '%' " 
			+ "			)IP"
			+ "			WHERE ROWNUM &lt;= #{pageDTO.limit} " 
			+ "		)TBL"
			+ "		WHERE NUM &gt;= #{pageDTO.offset}"
			+ "</script>";
	
	
	public String insert_sql() {
		return new SQL() {{
		INSERT_INTO ("tbl_books");
		INTO_COLUMNS("b_code");
		INTO_COLUMNS("b_name");
		INTO_COLUMNS("b_auther");
		INTO_COLUMNS("b_comp");
		INTO_COLUMNS("b_year");
		INTO_COLUMNS("b_iprice");
		
		INTO_VALUES("#{b_code,jdbcType=VARCHAR}");
		INTO_VALUES("#{b_name,jdbcType=VARCHAR}");
		INTO_VALUES("#{b_auther,jdbcType=VARCHAR}");
		INTO_VALUES("#{b_comp,jdbcType=VARCHAR}");
		INTO_VALUES("#{b_year,jdbcType=VARCHAR}");
		INTO_VALUES("#{b_iprice,jdbcType=VARCHAR}");
		}}.toString();
	}
	
	public String update_sql() {
		return new SQL() {{
		UPDATE ("tbl_books");
		WHERE("b_code = #{b_code,jdbcType=VARCHAR}");
		SET("b_name = #{b_name,jdbcType=VARCHAR}");
		SET("b_auther = #{b_auther,jdbcType=VARCHAR}");
		SET("b_comp = #{b_comp,jdbcType=VARCHAR}");
		SET("b_year = #{b_year,jdbcType=VARCHAR}");
		SET("b_iprice = #{b_iprice,jdbcType=VARCHAR}");
		
		}}.toString();
	}
}
