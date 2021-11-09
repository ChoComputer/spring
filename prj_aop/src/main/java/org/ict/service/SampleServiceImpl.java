package org.ict.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

	@Override
	public void introduce() {
		System.out.println("채종훈 입니다. 반갑습니다.");
		
	}

}
