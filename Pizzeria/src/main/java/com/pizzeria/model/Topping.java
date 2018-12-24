package com.pizzeria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="getAllToppings",query="select t from Topping t" )
})
		


@Entity
@Table(name = "topping")
public class Topping implements Serializable{

	
	@Id
	@Column(name="topping_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name="topping_name")
	private Name name;

	private static enum Name{
	PEPPERONI, MUSHROOMS, OLIVES, RED_PEPPER, PANEER,ONION, SAUSAGE, BACON, BLACK_OLIVES, GREEN_PEPPERS, PINEAPPLE, SPINACH,EXTRA_CHEESE
	}
	
	@Column(name="price")
	private Double price;


	public Topping() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Double getPrize() {
		return price;
	}

	public void setPrize(Double prize) {
		this.price = prize;
	}

	@Override
	public String toString() {
		return "Topping [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	

}
