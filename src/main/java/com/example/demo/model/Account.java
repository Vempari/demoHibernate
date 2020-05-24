package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

	@ManyToOne()
	@JoinColumn(name = "id_client")
	private Client client;

	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<Address> addresses = new ArrayList<>();

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", amount=" + amount +
				", currency='" + currency + '\'' +
				'}';
	}
}
