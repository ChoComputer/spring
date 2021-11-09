package org.ict.domain;

import java.sql.Date;
import java.util.List;

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
	// 게시판글에 리플갯수 수 표시하기위해 sql에서 추가했음
	private Long replyCount;
	
	private List<BoardAttachVO> attachList;
}
