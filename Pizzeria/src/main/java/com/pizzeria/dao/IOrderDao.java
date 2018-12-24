package com.pizzeria.dao;

import java.util.List;

import org.json.JSONObject;

import com.pizzeria.model.Order;

public interface IOrderDao {

	public List<Order> getAllOrder();

	List<JSONObject> getOrdersCountPizzaTypeWise();
}
