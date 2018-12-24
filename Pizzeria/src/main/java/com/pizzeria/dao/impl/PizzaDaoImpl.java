package com.pizzeria.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.pizzeria.dao.IPizzaDao;
import com.pizzeria.generic.impl.GenericImpl;
import com.pizzeria.model.Pizza;

@Repository("PizzaDaoImpl")
public class PizzaDaoImpl  extends GenericImpl<Pizza, Integer> implements IPizzaDao{

	

//	static EntityManager entityManager = HibernateSessionFactoryContainer.getEntityManager();
	
   
	@Override
	public List<Pizza> getAllPizza() {
		System.out.println("In the getAllPizza method.");
		Query query = getEntityManager().createNamedQuery("getAllPizza");
		List<Pizza> resultList = query.getResultList();
		System.out.println("resultList = "+resultList);
		return resultList;
	}



	@Override
	public List<JSONObject> getOrderedPizza(String pizzaType,String crust, String topping) {
		try {
			String sqlQuery="select p.pid,p.pizzaName,p.pizza_type from pizza p inner join  pizza_crust pc on p.pid=pc.piz_id inner join crust c on pc.crust_id=c.crustId inner join pizza_topping pt on pt.pizza_id=p.pid inner join topping t on t.topping_id=pt.toppings_id  where c.crust_name='"+crust+"' and t.topping_name='"+topping+"' and p.pizza_type='"+pizzaType+"'";
			System.out.println("sqlQuery for getOrderedPizza = "+sqlQuery);
			Query query = getEntityManager().createNativeQuery(sqlQuery);
			List<Object[]> data = query.getResultList();
			JSONObject json = new JSONObject();
			List<JSONObject> pizzaList =new ArrayList<JSONObject>();
			
			for(Object obj[]:data) {
				System.out.println("id = "+obj[0]);
				System.out.println("name = "+obj[1]);
				System.out.println("type = "+obj[2]);
				
				json = new JSONObject();
				json.put("id", obj[0]);
				json.put("name", obj[1]);
				json.put("type", obj[2]);
				pizzaList.add(json);
				} 
			System.out.println("pizzaList after adding json = "+pizzaList);
			System.out.println("pizzaList = "+pizzaList);
			return pizzaList;
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred at @class : "+this.getClass().getName());
				return null;
			}
	} 
   
  
}