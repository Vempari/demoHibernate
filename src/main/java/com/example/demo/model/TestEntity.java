package com.example.demo.model;

import javax.persistence.*;

@Entity
public class TestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String stg;

	public TestEntity() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStg(String stg) {
		this.stg = stg;
	}

	public String getStg() {
		return stg;
	}
}
