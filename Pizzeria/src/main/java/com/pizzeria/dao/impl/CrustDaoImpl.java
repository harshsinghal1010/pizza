package com.pizzeria.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pizzeria.dao.ICrustDao;
import com.pizzeria.generic.impl.GenericImpl;
import com.pizzeria.model.Crust;
import com.pizzeria.model.Pizza.PizzaType;

@Repository("CrustDaoImpl")
public class CrustDaoImpl  extends GenericImpl<Crust, Integer> implements ICrustDao{

	@Override
	public List<Crust> getCrustByPizzaType(String pizzaType)  {
		System.out.println("In the getCrustByPizzaType method with named query @class : "+this.getClass().getName()+" @params: pizzaType = "+pizzaType);
		
		try {
//		String sqlQuery="select c.crustId,c.crust_name from crust c inner join pizza_crust cp on cp.crust_id=c.crustId inner join pizza p on p.pid=cp.piz_id where p.pizza_type='"+pizzaType+"'";
//		System.out.println("sqlQuery = "+sqlQuery);
			PizzaType pizzaTypeEnum = null;
			if(pizzaType.equalsIgnoreCase(PizzaType.INDIAN.toString())) {
				System.out.println("Name is PizzaType.INDIAN.toString()");
				pizzaTypeEnum = PizzaType.INDIAN;
			}else if(pizzaType.equalsIgnoreCase(PizzaType.ITALIAN.toString())) {
				System.out.println("Name is PizzaType.ITALIAN.toString()");
				pizzaTypeEnum = PizzaType.ITALIAN;
			}else if(pizzaType.equalsIgnoreCase(PizzaType.FRENCH.toString())) {
				System.out.println("Name is PizzaType.FRENCH.toString()");
				pizzaTypeEnum = PizzaType.FRENCH;
			}else if(pizzaType.equalsIgnoreCase(PizzaType.RUSSIAN.toString())) {
				System.out.println("Name is PizzaType.RUSSIAN.toString()");
				pizzaTypeEnum = PizzaType.RUSSIAN;
			}else if(pizzaType.equalsIgnoreCase(PizzaType.BRAZILIAN.toString())) {
				System.out.println("Name is PizzaType.BRAZILIAN.toString()");
				pizzaTypeEnum = PizzaType.BRAZILIAN;
			}
			
		Query query = getEntityManager().createNamedQuery("getCrustByPizzaType").setParameter("pizzaType",pizzaTypeEnum);
		List<Crust> crustList = query.getResultList();
		for(Crust crust:crustList) {
			System.out.println("id = "+crust.getCid());
			System.out.println("name = "+crust.getCrustName());
		} 
		System.out.println("crust List from Named Query = "+crustList);
		return crustList;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred at @class : "+this.getClass().getName());
			return null;
		}
		
		
	} 
   
	@Override
	public List<Crust> getAllCrusts() {
		List<Crust> resultList;
		try {
			System.out.println("In the getAllCrusts method @class: "+this.getClass().getName());
			Query query = getEntityManager().createNamedQuery("getAllCrusts");
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