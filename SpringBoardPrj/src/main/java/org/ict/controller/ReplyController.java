package org.ict.controller;

import java.util.List;

import org.ict.domain.ReplyVO;
import org.ict.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	// consume 소비(컨트롤러가 요청하는것)  / produces(컨트롤러가 생산하는 결과데이터) spring교안8(rest서버)
	// consumes는 이 메서드의 파라미터를 넘겨줄떄 어떤 형식으로 넘겨줄지를 설정 기본적으로 rest방식에서는 json을 사용합니다.
	// produeces는 입력받은 데이터를 토대로 로직을 실행한 다음
	// 사용자에게 결과로 보여줄 데이터의 형식을 나타냅니다.
	// jack
	@PostMapping(value="",consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	// produces에 TEXT_PLAIN_VALUE를 줬으므로 결과코드와 문자열을 넘김
	public ResponseEntity<String> register(
			// rest 컨트롤러에서 받는 파라미터 앞에
			// @RequestBody 어노테이션이 붙어야
			// consumes와 연결됨
			@RequestBody ReplyVO vo){
		// 깡통 entity를 먼저 생성
		ResponseEntity<String > entity =null;
		try {
			// 먼저 글쓰기 로직 실행 후 에러가 없다면....
			service.addReply(vo);
			entity= new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			//catch로 넘어왔다라는건 글쓰기 로직에 문제가 생긴 상황이기 때문에...
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@GetMapping(value="/all/{bno}",
			// 단일 숫자데이터bno만 넣기도하고
			// PathVariable 어노테이션으로 이미 입력데이터가 명시되었으므로 consumes는 따로안줘도됨
			// produces는 댓글목록이 xml로도 json으로 표현할수 있게 아래와 같이 두개다 작성
				produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") long bno){
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(
					service.listReply(bno), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 일반 방식이 아닌 rest 방식에서는 삭제로직을 post 가아닌
	// delete 방식으로 요청하기 때문에 @DeleteMapping으로 사용합니다
	@DeleteMapping(value="/{rno}",produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") long rno){
		ResponseEntity<String> entity =null;
		try {
			service.removeReply(rno);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity= new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH},value="/{rno}",
					consumes="application/json",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String>modify(
			// VO는 우선 payload(YARC에있는)에 적힌 데이터로 받아옴
			//@RequestBody가 붙은 VO는 payload에 적힌 데이터를 VO로 환산해서 가져옵니다.
			@RequestBody ReplyVO vo, 
			// 단, 댓글번호는 주소에 기입된 숫자를 자원으로 받아옴
			@PathVariable("rno") long rno){
		ResponseEntity<String> entity = null;
		try {
			// payload에는 reply만 넣어줘도 되는데 그이유는 rno는 요청주소로 받아오기때문
			// 단, rno를 주소로 받아오는 경우는 아직 replyVO에 rno가 세팅이 되지 않은 상태이므로 setter로 rno까지 지정해줍니다.
			vo.setRno(rno);
			service.modifyReply(vo);
			entity=new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
