package com.pizzeria.dao;

import java.util.List;

import com.pizzeria.model.Crust;


public interface ICrustDao {

	List<Crust> getCrustByPizzaType(String pizzaType);

	List<Crust> getAllCrusts();
}
