package org.ict.domain;

import lombok.Data;

@Data
public class PayVO {
	
	private String itemname;
	private Long amount;
	private String merchant_uid;

}
