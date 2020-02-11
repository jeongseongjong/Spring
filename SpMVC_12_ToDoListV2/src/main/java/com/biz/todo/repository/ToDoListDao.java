package com.biz.todo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.todo.domain.ToDoList;
/*
 * mybatis 3.4부터 mapper.xml을 사용하지 않는 새로운 형식이 등장
 * 
 * @select
 * @insert
 * @update
 * @delete
 * 
 * Annotation을 interface에 설정함으로써 
 * mapper.xml을 사용하지 않는 새로운 방식의 코드가 등장
 * 
 * 아직은 완전하게 mapper.xml에 사용하는 모든 코드를 사용하기가 어렵다.
 * 계속해서 version up이 되면서 점점 xml을 사용하지 않아도 되는 방식으로 
 * 발전하고 있다.
 */
public interface ToDoListDao {

	/*
	 * mybatis의 mapper를 대신하는 Annotation
	 */
	@Select("SELECT * FROM tbl_todoList ORDER BY td_seq")
	public List<ToDoList> selectAll();
	
	@InsertProvider(type=ToDoListSQL.class,method="insert_sql")
	public int insert(ToDoList todoList);

	
	/*
	 * mapper Annotation에는 문자열로 SQL문을 작성해야 하는데
	 * SQL문이 복잡할경우 별도의 String 문자열 변수를 불러와서
	 * 사용할 수 있다.
	 * 
	 * 이 때 SQL문을 작성하는 String 문자열 변수는 
	 * 반드시 final static String형식으로 작성해야한다.
	 */
	@Update(ToDoListSQL.complete_sql)
	public int complete(@Param("td_seq")long td_seq);

	
	/*
	 * ToDoListSQL 클래스에 정의된 alarm_sql method를 호출하여
	 * 동적쿼리를 가져와서 update를 수행하라
	 * 
	 * provider를 사용하는 이유
	 * 다른 클래스에서 SQL문을 가져올때는 PROVIDER를 넣어준다
	 * 어노테이션의 SQL문이 길경우 오류 발생 가능성이 있다.
	 */
	@UpdateProvider(type = ToDoListSQL.class, method="alarm_sql")
	public int alarm(@Param("td_seq")long td_seq );
	
	@Delete("DELETE FROM tbl_todoList WHERE td_seq = #{td_seq}")
	public int delete(long td_seq);
	
	@UpdateProvider(type=ToDoListSQL.class,method="update_sql")
	public int update(ToDoList toDoList);

	@Select("SELECT * FROM tbl_todoList WHERE td_seq = #{td_seq}")
	public ToDoList findBySeq(long td_seq);

	@Select("SELECT * FROM tbl_todoList WHERE like '%' || #{td_subject} || '%'")
	public List<ToDoList> findBySubject(String td_subject);
}
