package com.pizzeria.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.pizzeria.dao.IOrderDao;
import com.pizzeria.generic.impl.GenericImpl;
import com.pizzeria.model.Order;

@Repository("OrderDaoImpl")
public class OrderDaoImpl extends GenericImpl<Order, Integer> implements IOrderDao{


//	static EntityManager entityManager = HibernateSessionFactoryContainer.getEntityManager();
	
	
	@Override
	public List<Order> getAllOrder() {
		System.out.println("In the getAllOrder method.");
		Query query = getEntityManager().createNamedQuery("getAllOrder");
		List<Order> resultList = query.getResultList();
		System.out.println("resultList of orders = "+resultList);
		return resultList;
	}


	@Override
	public List<JSONObject> getOrdersCountPizzaTypeWise() {
		try {
			Query query = getEntityManager().createNamedQuery("getPizzaTypeWiseOrders");
			List<Object[]> resultList = query.getResultList();
			JSONObject json = new JSONObject();
			List<JSONObject> ordersCountList =new ArrayList<JSONObject>();
			
			for(Object obj[]:resultList) {
				System.out.println("count = "+String.valueOf(obj[0]));
				System.out.println("pizzatype = "+String.valueOf(obj[1]));
				json.put(String.valueOf(obj[1]), String.valueOf(obj[0]));
				} 
			ordersCountList.add(json);
			System.out.println("resultList of orders pizza type  wise = "+ordersCountList);
			return ordersCountList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}