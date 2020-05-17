package com.example.demo;

import com.example.demo.model.Account;
import com.example.demo.model.Client;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import javax.persistence.FlushModeType;
import java.util.List;

@DatabaseSetup("/data.xml")
public class MainTest extends DemoApplicationTests{

	@Test
	public void contextLoads() {
		setUp();

		Client client = session.get(Client.class, 10);
		List<Account> accounts = client.getAccounts();
		System.out.println(System.lineSeparator() + client + System.lineSeparator());
		accounts.forEach(System.out::println);
	}

	@Test
	public void cacheFirst() {
		setUp();

		Client client = session.load(Client.class, 10);
		System.out.println(client.getName());

		client = session.load(Client.class, 10);
		System.out.println(client.getName());

	}

	@Test
	void queryCache() {
		setUp();

//		session.setCacheMode(CacheMode.GET); // Стандартизация кэша для всех запросов

		Query query = session.createQuery("from Account ac where ac.amount = :amount")
				.setCacheable(true)//тут кэш включается
//				.setCacheMode(CacheMode.GET) //Это вариант использование CacheMode в запросе
				.setHint( "javax.persistence.cache.retrieveMode " , CacheRetrieveMode.USE )//Это можно использовать вместо CacheMode
				.setHint( "javax.persistence.cache.storeMode" , CacheStoreMode.BYPASS )
				.setCacheRegion("frontpages");//это логический разделитель памяти вашего кэша


		Account account = (Account) query.setParameter("amount", 150).uniqueResult();
		System.out.println(account.getAmount());

		account = (Account) query.setParameter("amount", 150).uniqueResult();

		System.out.println(account.getAmount());

	}

	@Test
	void flushTest() {
		setUp();
//		session.setFlushMode(FlushMode.AUTO);//Как видно deprecated
//		session.setFlushMode(FlushModeType.AUTO);

		Client client = new Client();
		em.persist(client);


		Number clientsSize = ((Number) session.createNativeQuery("select count(*) from Client")
				.setFlushMode(FlushMode.MANUAL)
				.uniqueResult()).intValue();
		System.out.println(clientsSize);

		session.flush();

		clientsSize = ((Number) session.createNativeQuery("select count(*) from Client")
				.uniqueResult()).intValue();
		System.out.println(clientsSize);
	}






}
