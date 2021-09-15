package org.ict.controller;

import java.util.List;

import org.ict.domain.BoardVO;
import org.ict.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// 의존성 추가 , 로깅기능 추가하기
@Controller  // 컨트롤러이므로 컨트롤러로 빈컨테이너에 컴포넌트 스캔 
@Log4j       // 로깅기능추가
@RequestMapping("/board/*")// 클래스 위에 작성시 이클래스를
// 사용하는 모든 메서드의 연결주소 앞에 /board/ 추가  / 각 컨트롤러별로 세부주소 부여 가능
@AllArgsConstructor // 의존성 주입설정을 위해 생성자만 생성 
//@Getter 이건 게터만
public class BoardController {
	
	// 컨트롤러에서는 서비스 실행 하고 서비스는 매퍼를 호출함
	@Autowired
	private BoardService service;
	
	@GetMapping("/list")  // get방식으로 주소연결
	public void list(Model model) {
		log.info("list로직 접속");
		// 전체글 정보를 얻어와서
		List<BoardVO> boardList = service.getList();
		// view파일에 list라는 이름으로 넘겨주기
		model.addAttribute("list",boardList);
		
		
		// 1. views 하위에 경로에 맞게 폴더및 .jsp 파일생성
		// 2. 부트스트랩을 적용해 게시글 목록 화면에 표시
		
	}
}
