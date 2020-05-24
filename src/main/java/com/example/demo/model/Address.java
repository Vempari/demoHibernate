package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_address", nullable=false)
	private Integer id;

	String address;

	@ManyToOne(optional=false)
	@JoinColumn(name = "id_account")
	private Account account;

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", address='" + address + '\'' +
				'}';
	}
}
