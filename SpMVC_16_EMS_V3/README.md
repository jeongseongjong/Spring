# EMS Project
* Email Management System Project V1
* 2020-01-20

### JPA-Hibernate, MySQL 연동 프로젝트
	<bean id="emsHiber" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
		<property name="url" value="jdbc:mysql://127.0.0.1:3306/emsDB?serverTimezone=Asia/Seoul"/>
		<property name="username" value="ems"/>
		<property name="password" value="ems"/>
	</bean>
	
* driverClassName : com.bysql.cj.jdbc.Driver를 사용한다. 
* url : 기본연결 주소에 쿼리값을 추가하는데 serverTimezone=Asia/Seoul로 설정
* 5.x에서는 SSL연결을 하지 않는 &useSSL=false 옵션을 사용해야 하며
* 8.x에서는 오류가 발생하니 추가하지 않는다.


### NAVER아이디로 로그인 구현
1. 네이버에게 LOGIN창을 보내달라고 요청
	-- 서버에서 setate라는 특별한 key를 하나 생성하고
		그값을 같이 보내야 한다.
2. 네이버는 LOGIN창을 다시 redirect해주고
3. 네이버가 보내는 login창에 로그인을 수행하고
	정상적으로 로그인이 실행되면
4. OAuth20 규격의 tokent을 보내주는데 이 토큰을 이용해서 
	네이버에 어떤 정보(회원정보)를 요청할 수 있다.