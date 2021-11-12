package org.ict.security;

import org.ict.domain.CustomUser;
import org.ict.domain.MemberVO;
import org.ict.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	// DB테이블 구조에 맞춰주기 위해 mapper가 필요함
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("유저 이름 확인 : " + username);
		
		MemberVO vo = mapper.read(username);
		
		log.info("확인된 유저이름으로 얻어온 정보 : " + vo);
		// vo가 얻어온 username이 없으면 null 리턴
		// 얻어온 username이 db에 존재하면 커스텀 DB정보 리턴
		return vo==null? null : new CustomUser(vo);
	}

}
