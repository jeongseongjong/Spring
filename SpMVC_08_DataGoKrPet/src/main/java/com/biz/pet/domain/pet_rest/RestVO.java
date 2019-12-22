package com.biz.pet.domain.pet_rest;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * java 1.7이상에서만 작동
 */
@XmlRootElement(name="rfcOpenApi")
public class RestVO {

	// RestBody 클래스를 포함하겠다.
	public RestBody body;
	
}
