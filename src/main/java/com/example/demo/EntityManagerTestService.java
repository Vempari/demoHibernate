package com.example.demo;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class EntityManagerTestService {

	@PersistenceContext
	private EntityManager entityManager;


}
