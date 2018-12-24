package com.pizzeria.dao;

import java.util.List;

import org.json.JSONObject;

import com.pizzeria.model.Topping;


public interface IToppingDao {

	List<JSONObject> getToppingsByPizzaType(String pizzaType);

	List<Topping> getAllToppings();
}
