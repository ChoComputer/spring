package org.ict.service;
import org.ict.domain.PayVO;
import org.ict.mapper.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PayServiceImpl implements PayService {
	
	@Autowired
	private PayMapper mapper;
	
	@Override
	public void insertPay(PayVO vo) {
		mapper.insertPay(vo);
	}
	
	

}
