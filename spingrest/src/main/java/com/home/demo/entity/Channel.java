package com.home.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "channel")
public class Channel {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Double price;

	/*
	 * @ManyToOne
	 *
	 * @JoinColumn(name = "account")
	 *
	 * @JsonIgnore private Account account;
	 */
	public Channel() {
	}

	public Channel(Long id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/*
	 * public Account getAccount() { return this.account; }
	 * 
	 * public void setAccount(Account account) { this.account = account; }
	 */
	@Override
	public String toString() {
		return "Channel [id=" + this.id + ", name=" + this.name + ", price=" + this.price + "]";
	}

}
