package com.pizzeria.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@NamedQueries({
	@NamedQuery(name="getAllPizza",query="select p from Pizza p"),
})

@Entity
@Table(name = "pizza")
public class Pizza implements Serializable{

	
	@Id
	@Column(name="pid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="pizzaName")
	private String name;

	
	@Column(name="is_Veg",nullable=false)
	private Boolean isVeg;
	
	@Column(name="pizza_type")
	@Enumerated(EnumType.STRING)
	private PizzaType pizzaType;
	
	public static enum PizzaType{
		INDIAN, ITALIAN, FRENCH, RUSSIAN, BRAZILIAN
	}
	
	@JoinTable(name = "pizza_topping", joinColumns = {
		    @JoinColumn(name = "pizza_id")}, inverseJoinColumns = {
		    @JoinColumn(name = "toppings_id")})	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Topping> toppings;
	
	
	@JoinTable(name = "pizza_crust", joinColumns = {
		    @JoinColumn(name = "piz_id")}, inverseJoinColumns = {
		    @JoinColumn(name = "crust_id")})	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Crust> crusts;
	
	@Column(name="description")
	private String description;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Pizza() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PizzaType getPizzaType() {
		return pizzaType;
	}

	public void setPizzaType(PizzaType pizzaType) {
		this.pizzaType = pizzaType;
	}

	
	
	public Set<Topping> getToppings() {
		return toppings;
	}

	public void setToppings(Set<Topping> toppings) {
		this.toppings = toppings;
	}

	public Set<Crust> getCrusts() {
		return crusts;
	}

	public void setCrusts(Set<Crust> crusts) {
		this.crusts = crusts;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsVeg() {
		return isVeg;
	}

	public void setIsVeg(Boolean isVeg) {
		this.isVeg = isVeg;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", pizzaType=" + pizzaType + ", toppings="
				+ toppings + ", crusts=" + crusts + ", description=" + description+ ", isVeg=" + isVeg+ "]";
	}

	
	

	


}
