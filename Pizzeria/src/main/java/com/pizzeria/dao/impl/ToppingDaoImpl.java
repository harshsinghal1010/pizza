package com.pizzeria.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.pizzeria.dao.IToppingDao;
import com.pizzeria.generic.impl.GenericImpl;
import com.pizzeria.model.Topping;

@Repository("ToppingDaoImpl")
public class ToppingDaoImpl extends GenericImpl<Topping, Integer>implements IToppingDao{


//	static EntityManager entityManager = HibernateSessionFactoryContainer.getEntityManager();
		
	
	@Override
	public List<JSONObject> getToppingsByPizzaType(String pizzaType)  {
		System.out.println("In the getToppingsByPizzaType method @class : "+this.getClass().getName()+" @params: pizzaType = "+pizzaType);
		
//		Query query = entityManager.createNamedQuery("getToppingListByPizzaType").setParameter("pizzaType", pizzaType);
//		String sqlQuery = "select * from pizza p where p.pizza_type='"+pizzaType+"'";
		try {
		String sqlQuery="select t.topping_id,t.topping_name,t.price from topping t inner join pizza_topping tp on tp.toppings_id=t.topping_id inner join pizza p on p.pid=tp.pizza_id where p.pizza_type='"+pizzaType+"'";
		System.out.println("sqlQuery = "+sqlQuery);
		Query query = getEntityManager().createNativeQuery(sqlQuery);
		List<Object[]> data = query.getResultList();
		JSONObject json = new JSONObject();
		List<JSONObject> toppingList =new ArrayList<JSONObject>();
		
		for(Object obj[]:data) {
			System.out.println("id = "+obj[0]);
			System.out.println("name = "+obj[1]);
			json = new JSONObject();
			json.put("id", obj[0]);
			json.put("name", obj[1]);
			json.put("price", obj[2]);
			toppingList.add(json);
			} 
		System.out.println("toppingList after adding json = "+toppingList);
		return toppingList;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred at @class : "+this.getClass().getName());
			return null;
		}
		
		
	} 
   
	@Override
	public List<Topping> getAllToppings() {
		List<Topping> resultList;
		try {
			System.out.println("In the getToppingsByPizzaType method @class: "+this.getClass().getName());
			Query query = getEntityManager().createNamedQuery("getAllToppings");
			resultList = query.getResultList();
			System.out.println("resultList = "+resultList);
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred at @class : "+this.getClass().getName());
			return null;
		}
	} 
   
  
}