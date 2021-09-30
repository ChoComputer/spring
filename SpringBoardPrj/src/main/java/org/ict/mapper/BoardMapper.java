package org.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.ict.domain.BoardVO;
import org.ict.domain.Criteria;
import org.ict.domain.SearchCriteria;

public interface BoardMapper {
	
	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을
	// 어노테이션을 이용해 작성해주세요.
	//@Select("SELECT * FROM board_tbl WHERE bno < 4")
	public List<BoardVO> getList(String keyword);
	
	// insert구문 실행용으로 메서드를 선언합니다.
	// VO내부에 적혀있는 정보를 이용해 insert를 합니다.
	// BoardVO를 매개로 insert 정보를 전달받음.
	public void insert(BoardVO vo);
	
	// insertSelectKey를 매퍼, 서비스, 컨트롤러에 적용시켜주세요.
	public void insertSelectKey(BoardVO vo);
	
	// 글 번호(Long bno)를 파라미터로 받아
	// 해당 글 번호에 해당하는 글을 리턴해 보여주는 메서드를 작성해주세요.
	// 메서드 이름은 select 입니다.
	// xml파일에 쿼리문도 작성해보겠습니다.
	public BoardVO select(Long bno);
	
	// 글 번호(Long bno)를 파라미터로 바당
	// 해당 글 번호에 해당하는 글을 삭제해주는 메서드를 작성해주세요.
	// 메서드 이름은 delete 입니다.
	// xml파일에 쿼리문도 작성해주시고
	// 테스트코드까지 만들어 실제로 삭제되는지 sqldeveloper로 봐주세요.
	public void delete(Long bno);
	
	// 글 수정 로직을 작성해보겠습니다.
	// BoardVO를 받아서 수정해줍니다.
	// 바꿀 내역은, title, content, writer는 vo에서 받아서
	// updatedate는 sysdate로
	// where 구문은 bno로 구분해서 처리합니다.
	// 수정로직을 작성해주시고, 테스트까지 해 주세요.
	public void update(BoardVO vo);
	
	// 페이징 처리를 하면서 조회할것이기 때문에
	// Criteria 정보를 파라미터로 제공해야
	// 몇 페이지의 글을 조회할지 정보를 같이 쿼리문에 전송할 수 있습니다.	
	public List<BoardVO> getListPaging(SearchCriteria cri);
	
	
	// 전체글을 가져오는 로직
	public int getTotalBoard(SearchCriteria cri);
	
	
}







