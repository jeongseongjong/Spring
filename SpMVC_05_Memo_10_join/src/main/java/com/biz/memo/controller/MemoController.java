package com.biz.memo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.memo.domain.CatVO;
import com.biz.memo.domain.MemoDTO;
import com.biz.memo.domain.UserDTO;
import com.biz.memo.service.MemoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/memo")

/*
 * SessionAttributes로 설정된 변수는 
 * response를 하기전에 서버의 기억장소 어딘가에 임시로 보관을 해둔다
 * web browser와 server간에 한번이라도 연결이 이루어 졌으면
 * Session~ 에 등록된 변수는 서버가 중단될 때까지 그값이 유지된다.
 * 
 * web은 특성상 클라이언트의 request를 서버가 받아서
 * response를 수행하고 나면
 * 모든정보가 사라지는 특성이 있다.
 * 이런 특성과는 달리 Spring 기반의 web은 일부 데이터를 
 * 메모리에 보관했다가 재사용하는 기법이 있다.
 * 그중 SessionAttribute라는 기능이 있다.
 * 
 * sessionAttributes()에 설정하는 문자열은
 * 클래스의 표준 객체생성 패턴에 의해 만들어진 이름
 */
@SessionAttributes("memoDTO")
@Controller
public class MemoController {

	/*
	 * SessionAttributes를 사용하기 위해서는
	 * 반드시 해당 변수를 생성하는 method가 controller에 있어야 하고
	 * 해당 메소드에는 @ModelAttribute("변수명")가 있어야 한다.
	 */
	@ModelAttribute("memoDTO")
	public MemoDTO makeMemoDTO() {
		
		MemoDTO memoDTO = new MemoDTO();
		return memoDTO;
	}
	
	
	@Autowired
	MemoService mService;
	
//	@GetMapping
//	@PostMapping
	
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(String search, Model model) {
		
		List<MemoDTO> memoList; 
		if(search == null || search.isEmpty()) {
			memoList = mService.getAllList();
		}else {
			memoList = mService.getSearchList(search);
		}
		
		
		model.addAttribute("MEMO_LIST", memoList);
		
		return "home";
	}
	
	/*
	 * SessionAttributes에서 설정한 변수는 @ModelAttribute를 설정해 두어야 한다.
	 * 단, 5.x 이하에서는 필수 
	 *	   5.x 이상에서는 선택
	 *
	 *만약 Attributes의 이름을 표준패턴이 아닌 임의의 이름으로 사용할 경우
	 *@ModelAttribute("객체이름")을 필수로 지정해 주어야 한다.
	 */
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(@ModelAttribute MemoDTO memoDTO,Model model, HttpSession httpSession) {
		
		UserDTO userDTO = (UserDTO)httpSession.getAttribute("userDTO");
		
		if(userDTO == null){
			model.addAttribute("LOGIN_MSG","TRY");
			return "redirect:/member/login";
		}
		
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
		
		memoDTO.setM_date(sd.format(date));
		memoDTO.setM_time(st.format(date));
		memoDTO.setM_auth(userDTO.getU_id());
		
		/*
		 * 메모작성을 시작할 때 자동으로 첫번째 radio를
		 * 선택한 상태가 되도록 하기
		 */
		List<CatVO> cats= mService.getCats();
		memoDTO.setM_cat(cats.get(0).getCatValue());
		
		model.addAttribute("CATS", mService.getCats());
		model.addAttribute("memoDTO", memoDTO);
		return "insert";
	}
	
	
	/*
	 * insert POST가 memoDTO를 수신할 때
	 * 입력 form에서 사용자가 입력한 값들이 있으면
	 * 그 값들을 memoDTO의 필드변수에 setting하고
	 * 
	 * 만약 없으면
	 * 메모리 어딘가에 보관중인 SessionAttributes로 설정된
	 * memoDTO변수에서 값을 가져와서 비어있는
	 * 필드 변수를 채워서 매개변수에 주입한다.
	 * 
	 * 따라서 form에서 보이지 않아도 되는 값들은
	 * 별도의 코딩을 하지않아도 자동으로 insert POST로 전송
	 * 
	 * 단, 이 기능을 효율적으로 사용하려면
	 * jsp 코드에서 Spring-form tag로 input를 코딩해야 한다.
	 */
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute MemoDTO memoDTO, String search, Model model, SessionStatus sStatus) {
		
		log.debug("시퀀스 : " + memoDTO.getM_seq());
		log.debug("날짜 : " + memoDTO.getM_date());
		log.debug("text : " + memoDTO.getM_text());
		
		int ret = mService.insert(memoDTO);
		
		// SessionAttributes를 사용할 때 insert, update가 완료되고
		// view로 보내기전 반드시 setComplete()를 실행해서 
		// session에 담긴 값을 clear해주어야 한다.
		sStatus.setComplete();
		
		return "redirect:/memo/list";
	}
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(@RequestParam("id") String str_seq, @ModelAttribute MemoDTO memoDTO, Model model, HttpSession hSession) {
		
		long m_seq = 0;
		
		try {
			m_seq = Long.valueOf(str_seq);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		memoDTO = mService.getMemo(m_seq);
		UserDTO userDTO = (UserDTO)hSession.getAttribute("userDTO");
		// 로그인한 id와 메모의 m_auth 값을 비교하여
		// 작성자가 아니면 로그인 창으로 건너 뛰기
		
		if(userDTO != null && userDTO.getU_id().equals(memoDTO.getM_auth())) {
			model.addAttribute("memoDTO", memoDTO);
			return "view";
		}else {
			model.addAttribute("LOGIN_MSG", "NO_AUTH");
			return "redirect:/member/login";
		}
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(String id, @ModelAttribute MemoDTO memoDTO, Model model) {
		
		long m_seq = 0;
		
		try {
			m_seq = Long.valueOf(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		memoDTO = mService.getMemo(m_seq);
		model.addAttribute("CATS", mService.getCats());
		model.addAttribute("memoDTO", memoDTO);
		
		return "insert";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute MemoDTO memoDTO, Model model, SessionStatus sStatus ) {
		
		int ret = mService.update(memoDTO);
		
		sStatus.setComplete();
		return "redirect:/memo/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@ModelAttribute MemoDTO memoDTO) {
		
		int ret = mService.delete(memoDTO.getM_seq());
		
		return "redirect:/memo/list";
	}
}
