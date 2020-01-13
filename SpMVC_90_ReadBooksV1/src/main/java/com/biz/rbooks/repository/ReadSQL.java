package com.biz.rbooks.repository;

import org.apache.ibatis.jdbc.SQL;

public class ReadSQL {

	public String r_insert_sql() {
		return new SQL() {{
				INSERT_INTO("tbl_read_book");
				INTO_COLUMNS("rb_seq");
				INTO_COLUMNS("rb_bcode");
				INTO_COLUMNS("rb_date");
				INTO_COLUMNS("rb_stime");
				INTO_COLUMNS("rb_rtime");
				INTO_COLUMNS("rb_subject");
				INTO_COLUMNS("rb_text");
				INTO_COLUMNS("rb_star");

				INTO_VALUES("SEQ_READ_BOOK.NEXTVAL");
				INTO_VALUES("#{rb_bcode,jdbcType=VARCHAR}");
				INTO_VALUES("#{rb_date,jdbcType=VARCHAR}");
				INTO_VALUES("#{rb_stime,jdbcType=VARCHAR}");
				INTO_VALUES("#{rb_rtime,jdbcType=VARCHAR}");
				INTO_VALUES("#{rb_subject,jdbcType=VARCHAR}");
				INTO_VALUES("#{rb_text,jdbcType=VARCHAR}");
				INTO_VALUES("#{rb_star,jdbcType=VARCHAR}");
			}
		}.toString();
	}
	
	public String r_update_sql() {
		return new SQL() {{
			UPDATE ("tbl_read_book");
			SET("rb_bcode = #{rb_bcode,jdbcType=VARCHAR}");
			SET("rb_date = #{rb_date,jdbcType=VARCHAR}");
			SET("rb_stime = #{rb_stime,jdbcType=VARCHAR}");
			SET("rb_rtime = #{rb_rtime,jdbcType=VARCHAR}");
			SET("rb_subject = #{rb_subject,jdbcType=VARCHAR}");
			SET("rb_text = #{rb_text,jdbcType=VARCHAR}");
			SET("rb_star = #{rb_star,jdbcType=VARCHAR}");
			WHERE("rb_seq = #{rb_seq,jdbcType=VARCHAR}");
			}}.toString();
	}
}
