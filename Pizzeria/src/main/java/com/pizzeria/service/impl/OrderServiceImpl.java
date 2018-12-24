package com.pizzeria.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizzeria.dao.IOrderDao;
import com.pizzeria.generic.impl.GenericImpl;
import com.pizzeria.model.Order;
import com.pizzeria.service.IOrderService;

@Transactional
@Service("OrderService")
public class OrderServiceImpl extends GenericImpl<Order,Integer> implements  IOrderService{

	
	@Autowired
	private IOrderDao orderDao;
   
	OrderServiceImpl(){
		super(Order.class);
	}
	
	@Override
	public Order create(Order order) {
		System.out.println("In the create method of OrderServiceImpl class with pizza order cascade none ");
		Order createdOrder = super.create(order);
		System.out.println("Now the transaction is enabled but @transactional is commented");
		return createdOrder;
	}

	@Override
	public Order update(Order order) {
		System.out.println("In the update method of OrderServiceImpl class");
		Order createdOrder =  super.update(order);
		System.out.println("Now the transaction is enabled");
		return createdOrder;
	}
	
	@Override
	public Map<Object,Object> search(String orderBy,String orderType,String searchString){
		System.out.println("In the search method, orderBy = "+orderBy+" ,orderType = "+orderType+" ,searchString = "+searchString);
		return super.search(orderBy, orderType,searchString);
	}

	@Override
	public Order findById(Integer pkID) {
		System.out.println("In the method findById @class :"+this.getClass()+" with @params pkID: "+pkID);
		Order order = super.findById(pkID);
		System.out.println("Found pizza at @class :"+this.getClass()+" order = "+order);
		return order;
	}

	@Override
	public List<Order> findAll() {
		System.out.println("In the service impl going to retrieve the list");
		 List<Order> list = super.findAll();
		 System.out.println("list in service= "+list);
		return list;
	}

	@Override
	public Map<String,String> delete(Order order) {
		return super.delete(order);
	}

	@Override
	public Map<String,String> deleteById(Integer pkID) {
		Map<String, String> deleteById = super.deleteById(pkID);
		System.out.println("In the service impl deleteById = "+deleteById);
		return deleteById;
	}

	@Override
	public List<Order> getAllOrder() {
		System.out.println("In the service @class : "+this.getClass().getName()+" @Method : getAllOrder");
		List<Order> orderList=orderDao.getAllOrder();
		return orderList;
	}

	@Override
	public List<JSONObject> getOrdersCountPizzaTypeWise() {
		System.out.println("In the getOrdersCountPizzaTypeWise with Spring bean");
		List<JSONObject> ordersCountPizzaTypeWise = orderDao.getOrdersCountPizzaTypeWise();
		return ordersCountPizzaTypeWise;
	}


}