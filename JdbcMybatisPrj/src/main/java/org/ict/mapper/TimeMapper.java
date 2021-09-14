package org.ict.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	@Select("SELECT SYSDATE FROM DUAL")
	public String getTime();
	
	// 기본적으로 복잡한 쿼리문 작성을 하려면 xml파일과 인터페이스를 연동합니다.
	// 먼저, 인터페이스에는 아래와 같이 메서드를 선언만 해줍니다.
	// 그리고 같은 패키지 내부에 xml파일을 생성해놓고 거기에 실제로 getTime2가 호출될때
	// 실행할 실행문을 작성합니다.
	public String getTime2();
	
	// getTime3를 만들어서 테스트코드에서 실행까지 해 주세요
	public String getTime3();
}
