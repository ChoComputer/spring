package org.ict.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyVO {
	//스프링교안8 rest서버편
	private long rno;
	private long bno;
	private String reply;
	private String replyer;
	private Date replydate;
	private Date updatedate;
}
