package org.ict.mapper;

import java.util.List;

import org.ict.domain.ReplyVO;

public interface ReplyMapper {
	
	// 특정 게시판 bno글의 전체 댓글 목록 가져오기
	public List<ReplyVO> getList(long bno);
	
	public void create(ReplyVO vo);
	
	public void update(ReplyVO vo);
	
	public void delete(long rno);
	
	//글삭제할시 리플카운트 -1 하는 메서드  추가카운트는 보드에 감소카운트는 리플에 - 트랜잭션으로 연동시킬거임?
	public Long getBno(long rno);

}
