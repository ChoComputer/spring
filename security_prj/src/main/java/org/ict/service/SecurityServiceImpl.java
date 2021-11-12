package org.ict.service;

import org.ict.domain.MemberVO;
import org.ict.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private MemberMapper mapper;
	
	// 트랜잭션 걸면 더 좋음
	@Override
	public void insertMember(MemberVO vo) {	
		mapper.insertMemberTbl(vo);
		
		mapper.insertMemberAuth(vo);
	}
	@Override
	public MemberVO read(String userid) {
		return mapper.read(userid);
	}
}
