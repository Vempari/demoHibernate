package com.example.demo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestExecutionListeners({
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class
})
@Transactional
abstract class DemoApplicationTests {

	protected static final String[] DB_UNIT_SET_UP = {"",
			"****************************************************************",
			"*************** DATABASE HAS BEEN ALREADY SET UP ***************",
			"****************************************************************"
	};


	@PersistenceContext
	protected EntityManager em;

	protected Session session;

	protected void setUp() {
		session = em.unwrap(Session.class);

		Arrays.stream(DB_UNIT_SET_UP).forEach(System.out::println);
	}


}
