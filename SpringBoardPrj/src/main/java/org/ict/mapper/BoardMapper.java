package org.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.ict.domain.BoardVO;

public interface BoardMapper {
	
	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을
	// 어노테이션을 이용해 작성해주세요.
	//@Select("SELECT * FROM board_tbl WHERE bno < 4") // 메서드 중복됨 밑의 xml파일과 할수있지만 나누는게 조음 이거쓰면 xml에 작성하면안됨
	public List<BoardVO> getList();
	
	// insert 구문 실행용으로 메서드를insert를 합니다.
	// BoardVO를 매개로 in 선언합니다.
	// VO내부에 적혀있는 정보를 이용해 sert 정보를 받음
	public void insert(BoardVO vo);
	
	// 글번호(Long bno)를 파라미터로 받아
	// 해당 글 번호에 해당하는 글을 리턴해 보여주는 메서드를 작성해주세요.
	// 메서드 이름은 select입니다.
	//xml파일에 쿼리문도 작성해 보겠습니다
//	public void select(long bno); 내가 한거 잘못됬음
	public BoardVO select(Long bno);
	
	// 글번호(Long bno)를 파라미터로 받아
	// 해당 글 번호에 해당하는 글을 삭제해주는 메서드를 작성하기
	// 메서드 이름은 delete
	// xml파일에 쿼리문도 작성하기
	// 테스트 코드까지 만들어 식제로 삭제되는지 sqldeveloper로 보기
	
	public void delete(Long bno);
	
	// 글 수정 로직을 작성해보겠습니다.
	// BoardVO를 받아서 수정해 줍니다.
	// 바꿀 내역은, title,content,writer는 vo에서 받아서
	// updatedate는 sysdate로
	// where 구문은 bno로 구분해서 처리합니다.
	// 수정로직을 작성해 주시고, 테스트까지 하기
	
	public void update(BoardVO vo);
	
	
}
