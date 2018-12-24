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
	
	@NamedQuery(name = "getAllCrusts", query = "select c from Crust c"),
	@NamedQuery(name = "getCrustByPizzaType", query = "select c from  Pizza p inner join p.crusts c where p.pizzaType=:pizzaType"),

})

@Entity
@Table(name = "crust")
public class Crust implements Serializable {

	@Id
	@Column(name = "crustId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "crust_name")
	@Enumerated(EnumType.STRING)
	private CrustName crustName;

	private static enum CrustName {
		FRESH_PAN, THIN, CHEESE_BURST,PIZZA_BUGGELS, FLATBREAD, SILICAN, NEAPOLITAN, THICK, CHEESE_STUFFED, FOCACCIA 
	}

	public Integer getCid() {
		return id;
	}

	public void setCid(Integer cid) {
		this.id = cid;
	}

	public CrustName getCrustName() {
		return crustName;
	}

	public void setCrustName(CrustName crustName) {
		this.crustName = crustName;
	}

	@Override
	public String toString() {
		return "Crust [id=" + id + ", crustName=" + crustName + "]";
	}

}
