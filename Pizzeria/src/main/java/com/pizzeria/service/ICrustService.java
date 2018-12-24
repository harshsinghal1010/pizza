package com.pizzeria.service;

import java.util.List;

import com.pizzeria.generic.IGeneric;
import com.pizzeria.model.Crust;

public interface ICrustService extends IGeneric<Crust, Integer>{

	List<Crust> getCrustByPizzaType(String pizzaType);

	List<Crust> getAllCrusts();

}
