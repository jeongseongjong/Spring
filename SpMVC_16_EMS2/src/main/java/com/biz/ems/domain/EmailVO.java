package com.biz.ems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder


/*
 * @Entity : import를 persistence로 해야한다.
 * 	- JPA 인터페이스에 정의된 속성 지정
 *  - 지금부터 이 클래스는 Entity이므로 
 *  	물리적 table과 연동되도록 준비하라
 *  
 *  속성중에 다음항목이 지정되어 있으면 
 *  	<prop key="hibernate.hbm2ddl.auto">create</prop>
 *  Entity에 지정된 속성에 따라 table을 생성하라
 *  create value : drop and create
 *  지정하지 않으면 기본값이 create 이다.
 *  update로 지정을 했을 때 table이 없으면 생성을 하고
 *  만약 물리적 table과 구조가 다르면 오류가 발생
 *  
 *  <주의>
 *    - 만약 hiber.hbm2ddl.auto 속성을 지정하지 않으면
 *    	기본값으로 create가 지정되어 기존의 테이블을
 *    	Drop한 후 재생성 한다.(데이터 다날아감)
 *    
 *  none : 기본값, 아무일도 하지 않는다.
 *  create-only : 테이블을 새로 생성
 *  drop : 삭제
 *  create : drop and create
 *  create-drop : 시작할 때 drop and create를 수행하고
 *  			  session이 끝나면 다시 drop을 한다.
 *  
 *  validate : 검증만 하고
 *  update : 스키마를 갱신, 없으면 만들고 있으면 validate 수행
 */
@Entity
@Table(name="tbl_ems", schema = "emsDB")
public class EmailVO {
/*
 * 보내는메일
 * 받는메일
 * 보내는사람이름
 * 받는사람이름
 * 제목
 * 내용
 * 작성일자
 * 작성시각
 */
	/*
	 * 필드 변수에 @Id, @Column 속성지정
	 * - 테이블의 칼럼으로 생성
	 * @Id : PRIMARY KEY 칼럼으로 생성
	 * @GeneratedValue() 속성을 AUTO로 지정하면
	 * 	auto_increment를 수행하여 insert할 때
	 * 	자동 값 채우기가 된다.
	 * 
	 * table의 칼럼명은 ems_seq로 지정하고
	 * VO클래스의 변수명은 emsSeq로 하겠다.
	 */
	@Id // primary key로 선언 하겠다. / generationType.auto : 자동으로 생성해라
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ems_seq")
	private long ems_seq;
	
	/*
	 * @Column()지정하는 속성
	 * nullable : not null과 연관
	 * nullable = false : not null과 같다.
	 * nullable = true : default가 null이다.
	 * 
	 * length : 칼럼의 길이를 지정하지않으면
	 * 			DB기본값으로 255가 설정된다.
	 * 칼럼을 생성할 때는 필드변수의 이름과 같이 생성
	 */
	@Column(name="from_email", nullable = false, length = 20)
	private String from_email;
	
	/*
	 * @Column(name="to_email")
	 * 필드변수와 칼럼의 이름을 달리 설정하고자 할 때
	 */
	// 변수명이 자동으로 칼럼이름으로 생성된다(camel기법으로)
	// name으로 따로 지정되어있을경우 name=??에 들어가있는 변수가 칼럼명이 된다.
	@Column(name="to_email", nullable = false)
	private String to_email;
	
	/*
	 * @Column(columnDefinition = "")
	 * 칼럼의 type을 DB의 고유한 type으로 강제 적용
	 */
	@Column(name="from_name", 
			nullable = true, 
			columnDefinition = "nVARCHAR(20)")
	private String from_name;
	
	@Column(nullable = true,columnDefinition = "nVARCHAR(20)")
	private String to_name;
	
	@Column(nullable = false,columnDefinition = "nVARCHAR(100)")
	private String subject;
	
	@Column(name="content", nullable = false,columnDefinition = "nVARCHAR(1000)")
	private String content;
	
	@Column(name="send_date", nullable = true)
	private String send_date;
	
	@Column(name="send_time", nullable = true)
	private String send_time;
	
}
