package com.pizzeria.service;

import java.util.List;

import org.json.JSONObject;

import com.pizzeria.generic.IGeneric;
import com.pizzeria.model.Pizza;


public interface IPizzaService  extends IGeneric<Pizza, Integer>{

	public List<Pizza> getAllPizza();

	List<JSONObject> getOrderedPizza(String pizzaType, String crust, String topping);
	
}
