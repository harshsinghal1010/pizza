package com.pizzeria.service;

import java.util.List;

import org.json.JSONObject;

import com.pizzeria.generic.IGeneric;
import com.pizzeria.model.Topping;


public interface IToppingService extends IGeneric<Topping, Integer>{

	List<JSONObject> getToppingsByPizzaType(String pizzaType);

	List<Topping> getAllToppings();
	

}
