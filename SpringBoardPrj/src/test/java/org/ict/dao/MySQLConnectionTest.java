package org.ict.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

@Log4j
public class MySQLConnectionTest {
//	private final String DRIVER="com.mysql.cj.jdbc.Driver";
//	private final String URL =
//			"jdbc:mysql://127.0.0.1:3306/mysql?useSSL=false&serverTimezone=UTC";
//	private final String USER ="root";
//	private final String PW="mysql";
//	
//	@Test
//	public void testConnection() throws Exception{
//		Class.forName(DRIVER);
//		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
//			Log.info(con);
//			Log.info("mysql에 연결되었습니다.");
//		}
//	}
//	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
				
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	@Test
	public void testConnection() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/mysql?useSSL=false&serverTimezone=UTC",
				"root",
				"mysql")){
			Log.info(con);
			Log.info("mysql에 연결되었습니다.");
	}catch(Exception e) {
		fail(e.getMessage());
	}
	
}
}
