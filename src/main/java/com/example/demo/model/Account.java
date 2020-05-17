package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_account", nullable=false)
	private Integer id;

	private int amount;
	private String currency;

	@ManyToOne(optional=false)
	@JoinColumn(name = "id_client")
	private Client client;

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", amount=" + amount +
				", currency='" + currency + '\'' +
				'}';
	}
}
