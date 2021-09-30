package org.ict.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이징 처리를 할 때 페이지 하단에 현재 보고있는 페이지에 맞는
// 버튼을 깔아야 하는데, 이를 처리하려면 여러 정보가 동시에 전달 되어야해서
// 아예 페이징 처리에 필요한 정보를 묶어서 전달하기 위해
// Criteria 객체를 생성해서 사용합니다.
// 생성자를 커스터마이징해야해서 @DATA를 사용하지 않습니다.
@Getter
@Setter
@ToString
public class Criteria {

	// 페이지 번호, 페이지당 몇개의 글을 보여줄지에 대해
	// 먼저 저장하고 이를 이용해 나머지 정보를 계산합니다.
	private int pageNum;
	private int amount;

	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum,int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
	}
	
}
