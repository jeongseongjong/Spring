package com.biz.rbooks.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * tomcat이 작동하면서 제일먼저 호출할 클래스
 * pom.xml에 있는failOnMissingWebXml 요게 web.xml없이도 사용할수 있게 해주는 문자이다
 */
public class ProjectInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	/*
	 * web.xml의 root-context.xml을 대신할 메소드
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class};
	}

	/*
	 * servlet-context.xml을 대신할 메소드
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {

//		Class[] servlet = new Class[] {WebConfig.class};
//		return servlet;
		
		return new Class[] {
				WebServletConfig.class,
				MybatisConfig.class};
		
	}

	// 주소창에 어떤식으로 추가할지 정하는 메소드
	@Override
	protected String[] getServletMappings() {

//		String[] mapping = new String[] {"/","*.do"};
//		
//		return mapping;
		
		return new String[] {"/", "*.do"};
	}

}
