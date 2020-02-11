package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping(value = "/sample")
@Log4j
public class SampleController {

	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {

		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";
	}

	@RequestMapping(value = "/getSample", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public SampleVO getSample() {

		// VO객체에 들어있는 멤버변수의 값으로 순서대로 들어간다
		// mno : 112, firstName:스타, lastName : 로드
		return new SampleVO(112, "스타", "로드");

	}

	@RequestMapping(value = "/getSample2")
	public SampleVO getSample2() {

		return new SampleVO(113, "로켓", "라쿤");
	}

	@RequestMapping(value = "/getList")
	public List<SampleVO> getList() {

		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "First", i + "Last"))
				.collect(Collectors.toList());

	}

	@RequestMapping(value = "/getMap")
	public Map<String, SampleVO> getMap() {

		Map<String, SampleVO> map = new HashMap<>();
		// First라는 items? 범위? 안에 111, "그루트", "주니어"가 VO에 순서대로 주입된다
		map.put("First", new SampleVO(111, "그루트", "주니어"));

		return map;
	}

	@RequestMapping(value = "/check") // , params = {"height", "weight"}
	public ResponseEntity<SampleVO> check(Double height, Double weight) {

		// vo라는 객체에 0과 height, weight를 문자열로 바꿔 주입한다.(Double -> String)
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);

		ResponseEntity<SampleVO> result = null;

		// 만약 height값이 150미만이면
		if (height < 150) {

			// 502(BAD_GATEWAY)상태 코드(오류)와 데이터를 전송
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {

			// height가 150이상이라면 200코드와 데이터를 전송(
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}

		// 'sample/check.json?height=140&weight=60'과 같이 JSON타입의 데이터를 요구하고
		// height값을 150보다 작게하는 경우에는 502메시지와 함께 데이터가 전달된다
		return result;
	}

	// PathVariable을 적용하고 싶은경우 {}를 이용하여 변수명을 지정하고
	// 지정된 이름의 변수값을 얻을 수 있다. 
	// 값을 얻을때는 int, double과 같은 기본자료형은 사용할 수 없다.
	@RequestMapping(value = "/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {

		// 브라우저에서 sample/product/abc/123 이라고 호출하면 
		// cat과 pid변수의 값으로 처리되서 출력된다.
		/*
		 * <Strings>
		 * 	<item>category : abc </item>
		 * 	<item>productId : 123 </item>
		 * </String>
		 */
		return new String[] { 
				"category :" + cat, "productId " + pid };
	}

	@RequestMapping(value="/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("convert .....ticket" + ticket);
		
		return ticket;
	}

	
	
	
	
	
	
}


















