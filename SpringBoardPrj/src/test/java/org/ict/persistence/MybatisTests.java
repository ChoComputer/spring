package org.ict.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MybatisTests {

	
	// Connection => OJDBC 담당
	// DataSource => HikariCP 담당
	// SqlSessionFactory => Mybatis 담당
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testMybatis() {
		try(SqlSession session = sqlSessionFactory.openSession();
				Connection con = session.getConnection();){
			
			log.info(con);
			log.info(session);
			
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
}
