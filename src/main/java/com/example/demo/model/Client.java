package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_client")
	private Integer id;

	private String name;
	private int age;

	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private List<Account> accounts = new ArrayList<>();

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private List<City> cities = new ArrayList<>();

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}

}