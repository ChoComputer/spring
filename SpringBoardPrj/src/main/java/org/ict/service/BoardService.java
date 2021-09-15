package org.ict.service;

import java.util.List;

import org.ict.domain.BoardVO;

// 서비스 계층은, 하나의 동작을 담당합니다.
// mapper계층에서 하나의 메서드가 하나의 쿼리문만을 담당하였는데
// service계층은, 하나의 메서드가 2개 이상의 쿼리문을 담당할 수도 있으며
// 메서드 하나가 사용자의 하나의 동작단위를 담당합니다.

public interface BoardService {
	
	// 사용자의 동작 단위를 기술해 보겠습니다.
	// 글 등록
	public void register(BoardVO vo);
	
	// 글 조회
	public BoardVO get(Long bno);
	
	// 글 수정
	public void modify(BoardVO vo);
	
	// 글 삭제
	public void remove(Long bno);
	
	// 전체 글 목록
	public List<BoardVO> getList();

}
