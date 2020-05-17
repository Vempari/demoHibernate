package com.example.demo;

import com.example.demo.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnotherDemo {

	@Autowired
	TransactionalTest transactionalTest;

	@Autowired
	ExtendedScopeContext extendedScopeContext;

	@Test
	void PersistenceContext() {
		Client client = new Client();
		client.setName("Vasya");
		client.setAge(15);
		transactionalTest.insertWithoutTransaction(client);

		Client clientFromTransctionPersistenceContext = transactionalTest.find(1);

		System.out.println(clientFromTransctionPersistenceContext.getAge());

	}

	@Test
	void PersistenceContextExtended() {
//		Client client = new Client();
//		client.setName("Vasya");
//		client.setAge(15);
//
//		extendedScopeContext.insertWithTransaction(client);
//
//		Client clientFromTransctionPersistenceContext = extendedScopeContext.find(1);
//
//		System.out.println(clientFromTransctionPersistenceContext.getAge());


		Client client1 = new Client();
		client1.setName("Vasya");
		client1.setAge(15);
		extendedScopeContext.insertWithoutTransaction(client1);

//		System.out.println(extendedScopeContext.find(client1.getId()));

		Client client2 = new Client();
		client2.setName("Vasya");
		client2.setAge(15);
		extendedScopeContext.insertWithTransaction(client2);

		Client client1FromExtended = extendedScopeContext.find(client1.getId());
		Client client2FromExtended = extendedScopeContext.find(client2.getId());

		System.out.println(client1FromExtended);
		System.out.println(client2FromExtended);



	}
}
