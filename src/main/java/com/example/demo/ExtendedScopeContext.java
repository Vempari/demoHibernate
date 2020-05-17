package com.example.demo;

import com.example.demo.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Component
public class ExtendedScopeContext {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	protected EntityManager em;

	@Transactional
	public Client insertWithTransaction(Client client) {
		em.persist(client);
		return client;
	}

	public Client insertWithoutTransaction(Client client) {
		em.persist(client);
		return client;
	}

	public Client find(int id) {
		return em.find(Client.class, id);
	}
}
