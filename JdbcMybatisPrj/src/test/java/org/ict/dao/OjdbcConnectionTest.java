package org.ict.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import lombok.extern.log4j.Log4j;

// @Log4는 로깅기능을 쓸 수 있도록 도와줍니다.
// System.out.println(); 같은 경우는 로깅만을 목적으로
// 나온 기능이 아니기 때문에 메모리를 잡아먹습니다.
// 따라서 로그를 찍을때 System.out.println();을 쓰는건 권장되지 않습니다.
// 로깅만 할 수 있도록 Log4j를 사용합니다.
// 참고로 Log4j2는 spring-boot에서 쓰고, Log4j는 spring에서 씁니다.

@Log4j
public class OjdbcConnectionTest {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 이 클래스파일을 실행했을때, @Test가 붙은 메서드만 실행합니다.
	@Test
	public void testConnection() {
		try(Connection con = 
				DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XEPDB1", // 접속 주소 
						"mytest", // 계정아이디
						"mytest" // 계정비번
							)) {
			log.info(con);
			log.info("정상적으로 연결되었습니다.");
		} catch (Exception e) {
			// 접속이 정상적이지 않을때 출력할 내용을 아래에 작성합니다.
			// fail 함수를 이용해 우측 Failure Trace에 메세지를 띄웁니다.
			fail(e.getMessage());
		}
	}
	
	//@Test
	public void testConnection2() {
		log.info("이 코드는 실행 안됩니다.");
	}
}


