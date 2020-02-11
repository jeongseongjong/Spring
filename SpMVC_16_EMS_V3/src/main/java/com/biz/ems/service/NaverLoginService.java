package com.biz.ems.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.biz.ems.config.NAVER;
import com.biz.ems.domain.NaverMember;
import com.biz.ems.domain.NaverMemberResponse;
import com.biz.ems.domain.NaverReturnAuth;
import com.biz.ems.domain.NaverTokenVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NaverLoginService {

	@Value("${naver.client.id}")
	private String clientId ;
	
	@Value("${naver.client.secret}")
	private String clientSec ;
	
	// 네이버 아이디로 로그인 인정 요청 하는 URL
	private final String loginAPI_URL = "https://nid.naver.com/oauth2.0/authorize";
	
	// 접근 토큰 발급 / 갱신 / 삭제 요청 URL
	private final String tokenAPI_URL = "https://nid.naver.com/oauth2.0/token";
	private final String naverMemberAPI_URL = "https://openapi.naver.com/v1/nid/me";
	
//	private final String callbackLocalURL = "http://localhost:8080/ems/naver/ok";
	private final String callbackURL = "https://callor.com:12600/ems_jjong9950/member/naver/ok";
	
	public String oAuthLoginGet() {
		/*
		String redirectURI = null;
		
		try {
			
			redirectURI = URLEncoder.encode(callbackURL, "UTF-8"); 
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		SecureRandom random = new SecureRandom();
		
		// 최대값 130bit 크기의 임의의 정수를 생성하여 문자열로 만들어라 
		String stateKey = new BigInteger(130, random).toString();
		log.debug("STATE KEY : " + stateKey);
		
		
		/*
		 * spring 4.1에서 사용하는 UriQuery 문자열을 생성하는 클래스
		 * formHttpUrl() : 요청을 수행할 server 주소
		 * queryParam() : 변수 = 값 형태의 qeury문자열 생성
		 * build(true) : 생성하는 문자열 중에 encoding이 필요한 부분이 있으면
		 * 		encoding을 수행하라
		 * 5.1 이상에서는 별도로 encoding() method가 있다.
		 * queryParam("name","korea","usa") &name=korea&name=usa
		 */
		String apiURL = UriComponentsBuilder.fromHttpUrl(loginAPI_URL)
			.queryParam("client_id", this.clientId)
			.queryParam("response_type", "code")
			.queryParam("redirect_uri", callbackURL)
			.queryParam("state", stateKey)
			.build(true).toUriString();
		
		
//		String apiURL = loginAPI_URL; 
//		apiURL += "?client_id=" + this.clientId;
//		apiURL += "&response_type=code";
//		apiURL += "&redirect_uri=" + redirectURI;
//		apiURL += "&state=" + stateKey;
		
		log.debug("URL : " + apiURL);
		
		return apiURL;
		
	}
	
	/**
	 * 네이버에 정보요구를 할 때 사용할 토큰을 요청
	 * token을 요청할 때 GET/POST method를 사용할 수 있는데
	 * 여기서는 POST를 사용해서 요청을 하겠다.
	 * 
	 * @param naverOK
	 */
	public NaverTokenVO oAuthAccessGetToken(NaverReturnAuth naverOK) {
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("X-Naver-Client-Id", this.clientId);
		headers.set("X-Naver-Client-Secret", this.clientSec);
		
		/*
		 * Map interface를 상속받아 작성된 spring framework 전용 Map 인터페이스
		 * HttpProtocol과 관련된곳에서 기본 Map대신 사용하는 인터페이스
		 * HtppProtocol과 관련된 내장 method가 포함되어 있다.
		 */
		/*
		 * Http는 크게 봤을경우 그룹이 있는데(패키지라 한다)
		 * 위쪽에는 header 아래는 body(payload)라고 한다.
		 * header는 크기가 한정되어있다.
		 * 고로많은 양의 데이터를 싣지 못한다.
		 * 그래서 body에 싣어서 보낸다.
		 * HttpEntity는 구조를 자동으로 생성해서 보낸다.
		 */
		MultiValueMap<String, String> bodies = new LinkedMultiValueMap<String, String>();

		bodies.add(NAVER.TOKEN.grant_type, NAVER.GRANT_TYPE.authorization_code );
		bodies.add(NAVER.TOKEN.client_id, this.clientId );
		bodies.add(NAVER.TOKEN.client_secret, this.clientSec );
		bodies.add(NAVER.TOKEN.code, naverOK.getCode());
		bodies.add(NAVER.TOKEN.state, naverOK.getState());
		
		
		// headers에 담긴 정보와
		// params에 담긴 정보를
		// HttpEntity 데이터로 변환
		/*
		 * body부분에 parameter라는 문자열을 추가하고
		 * header부분에 위의 headers에서 설정한 형식으로 생성하고
		 * 		Authorization="Bearer ..." 
		 * 모두 문자열로 변환하여 Http Protocol의 데이터 구조로 변경한다.
		 * HttpProtocol을 사용하여 데이터를 전송할 수있도록
		 * 준비를 한다.
		 * HttpEntity<String> 형식으로 선언하면 : 단일 생성방식
		 * 
		 * HttpEntity<MultiValue<String,Object>> 형식으로 선언하면
		 * body 부분에 데이터를 다음과 같이 생성
		 * 변수 = [값],변수=[값]
		 * header부분의 데이터는 [변수:값, 변수 : 값]
		 */
		HttpEntity<MultiValueMap<String, String>> request 
		= new HttpEntity<MultiValueMap<String,String>>(bodies, headers);
		
		URI restURI = null;
		try {
			
			restURI = new URI(tokenAPI_URL);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * RestTemplate을 사용하여 네이버에 token을 요청
		 * RestTemplate : REST API 호출이후 응답을 받을 때까지 기다리는 동기방식
		 */
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<NaverTokenVO> result =
				// restTemp 에서 보낸다.
				// restURI로 POST 방식으로 Map(request)의 Data를 보내고 응답받는 데이터 형태는 NaverTokenVO 클래스이다. 
				restTemp.exchange(restURI, HttpMethod.POST,request,NaverTokenVO.class);
		
		log.debug("NAVER TOKEN : " + result.getBody().toString());
		
		return result.getBody();
	}
	
	public NaverMember getNaverMemberInfo(NaverTokenVO tokenVO) {
		
		String token = tokenVO.getAccess_token();
		String header = "bearer " + token ;
		
		// header 문자열을 GET의 http header에 실어서 
		// GET 방식으로 요청을 한다.
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", header);
		
		HttpEntity<String> request = new HttpEntity<String>("parameter", headers);
		
		ResponseEntity<NaverMemberResponse> result;
		RestTemplate restTemp = new RestTemplate();
		
		result = restTemp.exchange(naverMemberAPI_URL, HttpMethod.GET, request, NaverMemberResponse.class);
		
		NaverMember member = result.getBody().response;
		
		return member;
	}
	
}
