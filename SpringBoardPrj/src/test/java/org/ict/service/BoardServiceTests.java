package org.ict.service;

import static org.junit.Assert.assertNotNull;

import org.ict.domain.BoardVO;
import org.ict.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// Service테스트는 BoardServiceImpl 내부 기능을
// 서버 가동 없이 테스트하기 위해 작성합니다.
// 아래에 기본 세팅을 해주세요.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	"file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	// 다형성 원리에 의해서 BoardService로 만들어도
	// BoardServiceImpl 이 주입됨
	@Autowired
	private BoardService service;//=null;
	
	// 먼저 서비스가 제대로 주입되었는지 여부만 확인해봅니다.
	//@Test
	public void testExist() {
		log.info(service);
		// assertNotNull은 해당 객체가 주입이 되지 않아
		// null인경우 에러를 발생시킵니다.
		assertNotNull(service);
	}
	
	//@Test
	public void testRegister() {
		// register로직이 BoardVO를 필요로 하므로
		// 먼저 vo부터 생성해서 자료 입력 후 전달함
		BoardVO vo = new BoardVO();
		
		vo.setTitle("서비스작성글");
		vo.setContent("서비스본문글");
		vo.setWriter("서비스작성자");
		
		service.register(vo);
	}
	
	//@Test
	public void testGetList() {
		service.getList("");
	}
	
	//@Test
	public void testGet() {
		service.get(7L);
	}
	
	//@Test
	public void testModify() {
		// 수정로직도 수정사항 정보를 BoardVO에 담아서
		// 전달하기 때문에 BoardVO를 만들어놓고 자료를 저장한뒤 실행합니다.
		BoardVO vo = new BoardVO();
		
		vo.setBno(1L);
		vo.setTitle("서비스 수정된 글제목");
		vo.setContent("서비스 수정된 글본문");
		vo.setWriter("서비스 수정된 글쓴이");
		
		service.modify(vo);
	}
	
	//@Test
	public void testRemove() {
		service.remove(5L);
	}
	//@Test
	public void testgetPaging() {
		//페이징 코드를 이용해서 원하는 번호의 페이지가 잘 출력되는지 확인해주세요
		Criteria cri = new Criteria(5,10);
		// getListPaging 을 호출할때 Criteria가 필요하므로 위에 선언
		//service.getListPaging(cri);
		
	}
	
	
	
}




