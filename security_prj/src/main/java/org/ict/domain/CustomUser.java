package org.ict.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	
	// MemberVO자리에 다른 테이블구조에 맞춘 VO를 넣어주기만 하면 됨.
	private MemberVO member;
	
	public CustomUser(String username, String password,
			Collection<? extends GrantedAuthority> auth) {
		super(username, password, auth);
	}
	
	public CustomUser(MemberVO vo) {
		super(vo.getUserid(), vo.getUserpw(),
			vo.getAuthList().stream().map(author ->
				new SimpleGrantedAuthority(author.getAuth()))
			.collect(Collectors.toList()));
		
		// 내부변수 member에 vo를 대입
		this.member = vo;
	}	
}
