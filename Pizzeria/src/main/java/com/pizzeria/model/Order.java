package com.pizzeria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@NamedQueries({
	@NamedQuery(name="getAllOrder",query="select o from Order o"),
	@NamedQuery(name="getPizzaTypeWiseOrders",query="select count(*), p.pizzaType from Order o inner join  o.pizza p group by (p.pizzaType)"),
	
})

@Entity
@Table(name = "orders")
public class Order implements Serializable{
	
	
	@Id
	@Column(name="orderId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="order_name")
	private String name;

	
	public String getName() {
		return name;
	}

	public Pizza getPizza() {
		return pizza;
	}


	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	@OneToOne
	@JoinColumn(name="pizza")
	private Pizza pizza;
	
	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getOid() {
		return id;
	}

	public void setOid(Integer oid) {
		this.id = oid;
	}

	@Override
	public String toString() {
		return "Order [oid=" + id + ", name=" + name + "]";
	}

	public Order() {
		super();
	}

	public Order(Integer oid, String name) {
		super();
		this.id = oid;
		this.name = name;
	}

	
	

}
