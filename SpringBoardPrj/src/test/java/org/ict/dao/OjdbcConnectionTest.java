package org.ict.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

//@Log4j는 로깅기능을 쓸수 있도록 도와줍니다
// System.out.prinln(); 같은 경우는 로깅만을 목적으로
// 나온기능이 아니라서 메모리르 잡아먹음
// 따라서 로그를 찍을떄 System.out.ln();을 쓰는건 권장되지 않음
// 참고로 Log4j2는 spring-boot에서 쓰고 , log4j는 spring에서 씀
@Log4j
public class OjdbcConnectionTest {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 이 클래스 파일을 실행했을때, @Test가 붙은 메서드만 실행합니다.
	@Test
	public void testConnection() {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1",// 접속주소
				"mytest",// 계정아이디
				"mytest"// 계정비번
				)){
			Log.info(con);
			Log.info("정상적연결");
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	public void testConnection2() {
		Log.info("이코드는실행안됨");
	}

}
