package com.pizzeria.service.impl;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizzeria.dao.IPizzaDao;
import com.pizzeria.generic.impl.GenericImpl;
import com.pizzeria.model.Pizza;
import com.pizzeria.service.IPizzaService;

@Service("PizzaService")
@Transactional
public class PizzaServiceImpl extends GenericImpl<Pizza,Integer> implements  IPizzaService{

	@Autowired
	private IPizzaDao pizzaDao;
	
	PizzaServiceImpl(){
		super(Pizza.class);
	}
	
	@Override
	public Pizza create(Pizza t) {
		System.out.println("In the create method of PizzaServiceImpl class");
		Pizza createdPizza = super.create(t);
//		commitTransaction();
		return createdPizza;
	}

	@Override
	public Pizza update(Pizza t) {
		System.out.println("In the update method of PizzaServiceImpl class");
		Pizza createdPizza =  super.update(t);
//		commitTransaction();
		return createdPizza;
	}

	@Override
	public Map<Object,Object> search(String orderBy,String orderType,String searchString){
		System.out.println("In the search method, orderBy = "+orderBy+" ,orderType = "+orderType+" ,searchString = "+searchString);
		return super.search(orderBy, orderType,searchString);
	}
	
	@Override
	public Pizza findById(Integer pkID) {
		System.out.println("In the method findById @class :"+this.getClass()+" with @params pkID: "+pkID);
		Pizza pizza = super.findById(pkID);
		System.out.println("Found pizza at @class :"+this.getClass()+" pizza = "+pizza);
		return pizza;
	}

	@Override
	public List<Pizza> findAll() {
		System.out.println("In the service impl going to retrieve the list");
		 List<Pizza> list = super.findAll();
		 System.out.println("list in service= "+list);
		return list;
	}

	@Override
	public Map<String,String> delete(Pizza t) {
		return super.delete(t);
	}

	@Override
	public Map<String,String> deleteById(Integer k) {
		return super.deleteById(k);
	}


	@Override
	public List<Pizza> getAllPizza() {
		System.out.println("In the service @class : "+this.getClass().getName()+" @Method : getAllPizza with params : ");
		List<Pizza> pizzaList=pizzaDao.getAllPizza();
		return pizzaList;
	}

	@Override
	public List<JSONObject> getOrderedPizza(String pizzaType,String crust, String topping) {
		System.out.println("In the service @class : "+this.getClass().getName()+" @Method : getOrderedPizza with params : pizzaType = "+pizzaType+" & crust = "+crust+" & topping = "+topping);
		List<JSONObject> orderedPizza = pizzaDao.getOrderedPizza(pizzaType,crust, topping);
		return orderedPizza;
	}
}