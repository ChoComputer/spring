package org.ict.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {

	private Long bno;
	private String title;
	// 나머지도 작성해주세요
	// 시간은 Date로 작성합니다. java.sql 내부 자료입니다.
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
}
