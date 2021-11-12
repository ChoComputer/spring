package org.ict.service;

import org.ict.domain.MemberVO;

public interface SecurityService {

	public void insertMember(MemberVO vo);
    
	public MemberVO read(String userid);
}
