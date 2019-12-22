package com.biz.memo.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ScriptAssert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@ScriptAssert(lang="javascript", script="(_this.u_password == _this.re_password)",
			  reportOn= "re_password",
			  message="패스와드와 확인 패스워드가 불일치한다.")
public class UserDTO {

	/*
	 * @Email : email형식 검사
	 * @NotBlank : 공백검사
	 * @NotNull : Null 아닐경우 정상
	 * @Null : null일경우 정상
	 * @Max(x), @Min(x) : 숫자의 최대,최소값 제한
	 * @Size(min=x, max=x) : 문자열일 경우
	 * @DecimalMax(x) : x값이하의 실수
	 * @DecimalMin(x) : x값 이상의 십진수
	 * @Digits(integer=x) : x자릿수이하의 정수
	 * @Digits(integer=x, faction=y) : x 자릿수 이하의 정수이면서
	 * 								   y 자릿수 이하의 소수점 자릿수
	 */
	@Email(message = "이메일 형식으로 작성하라")
	@Size(min = 5, max = 5)
	private String u_id;
	private String u_password;
	
	private String re_password;
	private String u_name;
	
	@NotBlank(message = "닉네임은 공란이 안된다.")
	private String u_nick;
	private String u_grade;
	
	// 정규형 표현식
	@Pattern(regexp = "//d{1,15}", message="1~15까지의 숫자만 가능")
	private String u_tel;
	
}
