# Java Config 방식 SpringMVC Project
### 2020-01-17

* web.ml을 대신할 ProjectInit.java 클래스를 생성
- AbstractAnnotationConfigDispatcherServletInitializer를 상속받기

* root-context.xml을 대신할 RootConfig.java 클래스를 생성
- @Configuration을 클래스에 지정
- method는 없는 상태

* servlet=context.xml을 대신할 WebServletConfig.java클래스를 생성 
- @Configuration을 클래스에 지정
- @EnableWebMvc를 클래스에 지정
- @ComponentScan()을 클래스에 지정하고 controller와 service 패키지를 설정

- addResourceHandlers() method를 구현하는 코드 추가
- InternalResourceViewResolver를 생성하고 mapping을 설정하는 코드 추가

