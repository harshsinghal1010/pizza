package com.pizzeria.service;

import java.util.List;

import org.json.JSONObject;

import com.pizzeria.generic.IGeneric;
import com.pizzeria.model.Order;


public interface IOrderService  extends IGeneric<Order, Integer>{

	List<Order> getAllOrder();

	List<JSONObject> getOrdersCountPizzaTypeWise();

}
