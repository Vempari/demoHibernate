package com.example.demo;

import com.example.demo.model.Account;
import com.example.demo.model.City;
import com.example.demo.model.Client;
import com.example.demo.model.Product;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Transaction;
import org.hibernate.cache.spi.QueryCache;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

@DatabaseSetup("/data.xml")
public class MainTest extends DemoApplicationTests{

	@Test
	public void fetchMaxDepth() {
		setUp();
		Client client = session.get(Client.class, 10);
		System.out.println(System.lineSeparator() + client + System.lineSeparator());


		client.getAccounts().forEach(System.out::println);
		client.getAccounts().forEach(account -> account.getAddresses().forEach(System.out::println));
	}

	@Test
	public void statistic() {
		setUp();

		for (int i=0; i<10; i++) {
			Product p = new Product();
			p.setName("MyProduct"+i);
			session.flush();
			this.em.persist(p);
		}

		this.em.createQuery("Select p From Product p").setHint("org.hibernate.comment", "comment").getResultList();
	}

	@Test
	public void identifireRollback() {
		setUp();

		City city = new City();
		city.setCity("Novosibirsk");
		Transaction transaction = session.getTransaction();
		session.save(city);
		System.out.println("before the id is " + city.getId());
		em.flush();
		transaction.commit();

		transaction = session.beginTransaction();
		session.delete(city);
		transaction.commit();
		session.close();
		System.out.println("after the id is " + city.getId());
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
	public void cacheSecond() {
		setUp();

		Client client = session.load(Client.class, 10);
		System.out.println(client.getName());

		setUp();
		client = session.load(Client.class, 10);
		System.out.println(client.getName());
	}

	@Test
	void queryCache() {
		setUp();

//		session.setCacheMode(CacheMode.GET); // Стандартизация кэша для всех запросов

		Query query = session.createQuery("from Account ac where ac.amount = :amount")
				.setCacheable(true);//тут кэш включается
//				.setCacheMode(CacheMode.GET) //Это вариант использование CacheMode в запросе
//				.setHint( "javax.persistence.cache.retrieveMode " , CacheRetrieveMode.USE )//Это можно использовать вместо CacheMode
//				.setHint( "javax.persistence.cache.storeMode" , CacheStoreMode.BYPASS )
//				.setCacheRegion("frontpages");//это логический разделитель памяти вашего кэша


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
