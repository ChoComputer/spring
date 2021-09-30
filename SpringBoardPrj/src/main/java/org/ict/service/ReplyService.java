package org.ict.service;

import java.util.List;

import org.ict.domain.ReplyVO;

public interface ReplyService {
	
	public void addReply(ReplyVO vo);
	
	public List<ReplyVO> listReply(long bno);
	
	public void modifyReply(ReplyVO vo);
	
	public void removeReply(long rno);
}
