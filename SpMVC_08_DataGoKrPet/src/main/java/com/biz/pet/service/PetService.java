package com.biz.pet.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.pet.config.DataGoConfig;
import com.biz.pet.domain.GoPetVO;
import com.biz.pet.domain.pet_rest.RestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetService {

	private final String go_pet_url = "http://openapi.jeonju.go.kr/rest/dongmulhospitalservice";

	public String getQueryHeader() {

		String queryString = go_pet_url;

		queryString += "/getDongMulHospital";
		queryString += "?ServiceKey=" + DataGoConfig.DATA_GO_AUTH;
		queryString += "&pageNo=1";
		queryString += "&numOfRows=100";

		return queryString;
	}

	public List<GoPetVO> getRestVOList(String s_cat, String s_text) {

		String queryString = this.getQueryHeader();
		try {
			if(s_cat.equalsIgnoreCase("ADDR")) {
				queryString += "&address=" + URLEncoder.encode(s_text,"UTF-8");
			}else {
				queryString += "&dongName=" + URLEncoder.encode(s_text,"UTF-8");	
			}
			
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// HttpRequest Header 설정하기
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_XML));
// 		header.setAccept(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_JSON_UTF8));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", header);

		// Spring 3.2에서 도입된 새로운 HttpConnection의 추상화된 객체
		// RestTemplate : restApi 호출이후 응답을 받을 때까지 기다리는 동기방식
		RestTemplate restTemp = new RestTemplate();

		URI restURI = null;
		// ???
		// ResponseEntity<String> restList = null;
		ResponseEntity<RestVO> result = null;
		try {

			restURI = new URI(queryString);

			// String으로 return 해주겠다. / null -> 보낼때(JSON형태)
			result = restTemp.exchange(restURI, HttpMethod.GET, entity, RestVO.class);
			RestVO rVO = (RestVO)result.getBody();
			
			List<GoPetVO> goPetList = rVO.body.data.list;
			return goPetList;
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

//	public List<GoPetVO> getData(String s_text) {
//
//		String queryString = this.getRestString(s_text);
//		JsonElement jElement = JsonParser.parseString(queryString);
//
//		JsonObject oBody = (JsonObject) jElement.getAsJsonObject().get("body");
//		JsonObject oTotal = (JsonObject) oBody.getAsJsonObject().get("totalCount");
//		if (Integer.valueOf(oTotal.toString()) < 1) {
//			
//			return null;
//		}
//
//		JsonObject oData = (JsonObject) oBody.getAsJsonObject().get("data");
//
//		JsonArray oList = null;
//		List<GoPetVO> petList = null;
//		// JsonArray를 List로 변환
//		Gson gson = new Gson();
//
//		// list를 추출하여 JsonArray로 변환하는 과정에서 exception이 발생하면
//		// 추출된 데이터가 1개뿐일경우
//		try {
//
//			oList = (JsonArray) oData.getAsJsonObject().get("list");
//
//			// JsonArray를 List로 변환
//			TypeToken<GoPetVO> typeToken = new TypeToken<GoPetVO>() {
//			};
//			petList.add(gson.fromJson(oList, typeToken.getType()));
//
//			// 정상적으로 list를 모두 추출했을 경우는 전체 데이터의 list를 return
//			return petList;
//		} catch (ClassCastException e) {
//			// TODO: exception이 발생했을때는 1개짜리 list를 만들어서 return
//			log.debug("데이터가 1개 뿐이다");
//			JsonObject petObj = (JsonObject) oData.getAsJsonObject().get("list");
//			TypeToken<GoPetVO> typeToken = new TypeToken<GoPetVO>() {
//			};
//
//			petList.add(gson.fromJson(petObj, typeToken.getType()));
//			petList = new ArrayList<GoPetVO>();
//
//			return petList;
//		}
//
//	}

}
