package org.ict.service;

import java.util.List;

import org.ict.domain.ReplyVO;
import org.ict.mapper.BoardAttachMapper;
import org.ict.mapper.BoardMapper;
import org.ict.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardAttachMapper attachMapper;

	// 트랜잭션 사용하기위해서임 두개를 동시에 작동시키기위해 보드에해놓은 리플카운트를써먹기위해서다
	// 리플을 썼을때 board_tbl도 업데이트 해야하므로 board_tbl에 접근할수있는
	// BoardMapper도 같이 호출할수 있게 생성해야함
	@Autowired
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public void addReply(ReplyVO vo) {
		mapper.create(vo);
		// 리플갯수 증감 
		// 댓글은 rno로 추가되지만, 해당 글에 달린 댓글개수를 얻어야 하기 때문에
		// vo.getBno()를 쓰셨어야 합니다.
		boardMapper.updateReplyCount(vo.getBno(), 1L);
	}
	

	@Override
	public List<ReplyVO> listReply(long bno) {
		return mapper.getList(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(long rno) {
		// 글번호 얻어오기
		// mapper.delete가 실행되는 순간 , bno를 포함한 rno가 날라가기때문에
		// 먼저 bno를 얻어서 저장까지 한다음 rno번 로우를 삭제해야 마지막 로직실행가능
		Long bno = mapper.getBno(rno);
		mapper.delete(rno);
		
//		System.out.println("디버깅------------");
//		System.out.println("삭제한그번호 : "+bno);
//		System.out.println("디버깅------------");
		// 리플갯수 차감
		boardMapper.updateReplyCount(bno, -1L);
	}

}
