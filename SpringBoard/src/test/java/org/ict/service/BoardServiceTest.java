package org.ict.service;

import static org.junit.Assert.assertNotNull;

import org.ict.domain.BoardVO;
import org.ict.mapper.BoardMapperTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// Service 테스트는 BoardServiceImpl 내부 기능을
// 서버 가동 없이 테스트하기위해 작성합니다.
// 아래에 기본 세팅을 해주세요.

//테스트기본세팅
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		"file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {
	
	// 다형성 원리에 의해서 BoardServic로 만들어도
	// BoardServiceImpl이 주입됨
	@Autowired
	private BoardService service;
	
	// 먼저 서비스가 제대로 주입되었는지 여부만 확인하기
	//@Test
	public void testExist() {
		log.info(service);
		// assertNotnull은 해당 객체가 주입되지 않아
		// null인경우 에러발생
		assertNotNull(service);   // fail 과비슷 notnull일경우 실행 연동되면 초록색
	}
	//@Test
	public void testRegister() {
		// register로직이 BoardVO를 필요로 하므로
		// 먼저 vo부터 생성해서 자료입력후 전달
		BoardVO vo =new BoardVO();
		vo.setTitle("서비스작성글");
		vo.setContent("서비스글본문");
		vo.setWriter("서비스글작성자");
		service.register(vo);
		
	}
	
	//@Test
	public void testGetList() {
		
		service.getList();
		
	}
	//@Test
	public void testGet() {
		service.get(3L);
	}
	//@Test
	public void testModify() {
		
		// 수정로직도 수정사항 정보를 BoardVO에 담아서 
		// 전달하기때문에BoardVO를 만들어 놓고 자료를 저장한뒤 실행함
		BoardVO vo=new BoardVO();
		vo.setContent("수정글본문");
		vo.setTitle("수정글제목");
		vo.setWriter("수정글쓴이");
		vo.setBno(3L);
		
		service.modify(vo);
	}
	
	@Test
	public void testRemove() {
		service.remove(3L);
	}
}
