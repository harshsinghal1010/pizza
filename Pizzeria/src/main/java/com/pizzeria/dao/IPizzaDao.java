package com.pizzeria.dao;

import java.util.List;

import org.json.JSONObject;

import com.pizzeria.model.Pizza;


public interface IPizzaDao {

	public List<Pizza> getAllPizza();
	public List<JSONObject> getOrderedPizza(String pizzaType, String crust, String topping);
}
