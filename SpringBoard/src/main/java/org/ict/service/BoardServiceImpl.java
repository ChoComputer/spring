package org.ict.service;

import java.util.List;

import org.ict.domain.BoardVO;
import org.ict.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// BoardServiceImpl은 BoardService 인터페이스를 구현합니다.
// 의존성 주입을 위해
@Log4j // 로깅을위한 어노테이션  // x가뜨면 pom.xml에서 추가수정 필요
@Service // 의존성 등록을 위한 어노테이션
@AllArgsConstructor // 서비스 생성자 자동생성
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	
	// 등록작업시 BoardVO를 매개로 실행하기 때문에
	// 아래와 같이 BoardVO를 파라미터에 설정해둠.
	// BoardServiceTests에 VO를 생성해 테스트해주세요
	@Override
	public void register(BoardVO vo) {
		log.info("등록작업실행");
		mapper.insert(vo);
	}
	// 전체글을 가져오는게아닌 특정 글 하나만 가져오는 로직을 완성시키고
	// 테스트코드도 작성하여 테스트하기
	@Override
	public BoardVO get(Long bno) {
		BoardVO board=mapper.select(bno);
		log.info(bno+" 번 글 실행");
		
		return board;
	}

	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		log.info("수정작업시작-"+vo);
	       mapper.update(vo);
		
	}

	@Override
	public void remove(Long bno) {
		// TODO Auto-generated method stub
		mapper.delete(bno);
		log.info(bno+"번 쨰글 삭제실행");
		
	}
	
	// 글 전체 목록을 가져오는 로직 작성하기
	// 해당로직은 mapper 내부의 getList의 쿼리문을 먼저
	// 전체글을 가져오는 로직으로 수정한다음 service에 
	// 등록해 구현하기
	// 추가로 테스트도 진행하기
	@Override
	public List<BoardVO> getList(String keyword) {
		// TODO Auto-generated method stub
		log.info("전체글 가져오기 실행");
		List<BoardVO> boardList=mapper.getList(keyword);
		
		return boardList;
		
	}
	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
