package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_city", nullable=false)
	private Integer id;

	String city;

	@ManyToOne()
	@JoinColumn(name = "id_client")
	private Client client;

	@Override
	public String toString() {
		return "City{" +
				"id=" + id +
				", city='" + city + '\'' +
				'}';
	}
}

