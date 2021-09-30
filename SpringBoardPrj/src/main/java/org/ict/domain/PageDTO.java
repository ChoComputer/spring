package org.ict.domain;

import lombok.Getter;
import lombok.ToString;

// DTO는 Data TRansfer Object로 데이터 전달 객체라고 함
// DTO VO 는 엄격히 구분되지 않음 둘다 특정 데이터를 한번수에 묶어 보내기위해 사용
// 차이점이 있다면 (정해진 규칙은 아님) vo는 db에서 바로 꺼낸 데이터를 매칭시키고
// DTO는 DB에 있는 정보를 토대로 가공한 데이터를 전달할때 사용

@Getter
@ToString
public class PageDTO {
	
	// 페이지 네이션 버튼을 몇개나 깔지
	private int btnNum;
	
	// 페이지 세트중 시작하는 페이지 16페이지 조회중이라면 11이 시작페이지임
	private int startPage;
	
	//페이지 세트중 끝나는 페이지 16페이지 조회중이면 20이 끝페이지임
	private int endPage;
	
	//이전, 이후 10개 페이지 버튼 유무여부
	private boolean prev,next;
	
	//전체 데이터 갯수
	private int total;
	
	//페이지 , 글 정보
	private Criteria cri;
	
	//btnNum은 우선 10개로 간주
	public PageDTO(Criteria cri, int total, int btnNum) {
		this.cri=cri;
		this.total=total; 
		this.btnNum=btnNum;
		
		// 끝페이지를 먼저구하고
		// 끝페이지=올림((현재페이지/출력페이지,실수))*출력페이지
		this.endPage=(int)(Math.ceil(cri.getPageNum()/(double)this.btnNum)*cri.getAmount());

		// 다음 시작 페이지는 끝페이지-까는버튼수 +1을 해서 구함
		this.startPage=this.endPage-this.btnNum+1;
		
		// 순서상 startPage 다음 realEnd가 와야함 순서상!
		
		// 총페이지의 갯수가 10단위로 안끊어질수 있기 때문에 구하는 공식 
		// 예를 들어 757개의 글이 있으면 760까지 되는게 아닌 1~7로 끝나야 하기 때문에 나오는 공식임
		int realEnd=(int)(Math.ceil((total*1.0)/cri.getAmount()));
		if(realEnd<this.endPage) {
			this.endPage=realEnd;
		}
		
		// 1이전은 prev버튼이 필요없음 
		this.prev=this.startPage==1?false:true;
		
		this.next=this.endPage<realEnd;
		
		
		
		
	}
	

}
